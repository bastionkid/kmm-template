plugins {
    `kotlin-dsl`
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.spotless.gradlePlugin)
    compileOnly(libs.moko.resources.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidLibrary") {
            id = "android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("kotlinMultiplatform") {
            id = "kotlin.multiplatform"
            implementationClass = "KotlinMultiplatformConventionPlugin"
        }
        register("composeMultiplatform") {
            id = "compose.multiplatform"
            implementationClass = "ComposeMultiplatformConventionPlugin"
        }
        register("mokoResources") {
            id = "moko.resources"
            implementationClass = "MokoResourcesConventionPlugin"
        }
    }
}