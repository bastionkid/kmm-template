import dev.icerock.gradle.MRVisibility
import org.jetbrains.kotlin.gradle.plugin.mpp.BitcodeEmbeddingMode

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.cocoapods)
    alias(libs.plugins.android.library)
    alias(libs.plugins.moko.resources)
    alias(libs.plugins.compose.multiplatform)
}

@OptIn(
    org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class,
    org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl::class,
)
kotlin {
    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_17.toString()
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()
//    wasm()
    cocoapods {
        summary = "Shared Module"
        homepage = "Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../ios/Podfile")
        framework {
            baseName = "shared"
            isStatic = true // This is enabled to make moko-resources work on iOS
            transitiveExport = false // This is default
            embedBitcode(BitcodeEmbeddingMode.BITCODE)

            export(libs.moko.resources)
            export(libs.moko.graphics)

            extraSpecAttributes["resources"] = "['src/commonMain/resources/**', 'src/iosMain/resources/**']"
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.moko.resources)
                api(libs.moko.resources.compose)

                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.moko.resources.test)
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by getting {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
    }
}

android {
    namespace = "com.azuredragon.app"
    compileSdk = 33

    defaultConfig {
        minSdk = 26
    }

    sourceSets["main"].apply {
        manifest.srcFile("src/androidMain/AndroidManifest.xml")
        res.srcDirs("src/androidMain/res")
        resources.srcDirs("src/commonMain/resources")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "com.azuredragon.app" // required
    multiplatformResourcesClassName = "SharedRes" // optional, default MR
    multiplatformResourcesVisibility = MRVisibility.Public // optional, default Public
    iosBaseLocalizationRegion = "en" // optional, default "en"
    multiplatformResourcesSourceSet = "commonMain"  // optional, default "commonMain"
}