import org.jetbrains.kotlin.gradle.plugin.mpp.BitcodeEmbeddingMode

plugins {
    id("kotlin.multiplatform")
    id("moko.resources")
    id("compose.multiplatform")
}

kotlin {
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
        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.compose.ui.tooling)
                implementation(libs.androidx.compose.ui.tooling.preview)
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
}

multiplatformResources {
    multiplatformResourcesPackage = "com.azuredragon.app" // required
}