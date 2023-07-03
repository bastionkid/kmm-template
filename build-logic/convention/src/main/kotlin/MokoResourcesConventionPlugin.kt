import dev.icerock.gradle.MRVisibility
import dev.icerock.gradle.MultiplatformResourcesPluginExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class MokoResourcesConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("dev.icerock.mobile.multiplatform-resources")
            }

            extensions.configure<MultiplatformResourcesPluginExtension> {
                multiplatformResourcesClassName = "SharedRes" // optional, default MR
                multiplatformResourcesVisibility = MRVisibility.Public // optional, default Public
                iosBaseLocalizationRegion = "en" // optional, default "en"
                multiplatformResourcesSourceSet = "commonMain"  // optional, default "commonMain"
            }
        }
    }
}