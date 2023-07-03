import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl

class KotlinMultiplatformConventionPlugin: Plugin<Project> {

    @OptIn(ExperimentalKotlinGradlePluginApi::class, ExperimentalWasmDsl::class)
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.multiplatform")
                apply("com.android.library")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = 33
            }

            extensions.configure<KotlinMultiplatformExtension> {
                targetHierarchy.default()

                android()
                iosX64()
                iosArm64()
                iosSimulatorArm64()
//                wasm()
            }
        }
    }
}