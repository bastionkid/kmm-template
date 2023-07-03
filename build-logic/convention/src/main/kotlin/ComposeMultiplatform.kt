import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.getByType

internal fun Project.configureComposeMultiplatform(
    commonExtension: CommonExtension<*, *, *, *, *>,
) {
    commonExtension.apply {
        sourceSets["main"].apply {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
            res.srcDirs("src/androidMain/res")
            resources.srcDirs("src/commonMain/resources")
        }

        buildFeatures {
            compose = true
        }

        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

        composeOptions {
            kotlinCompilerExtensionVersion = libs.findVersion("androidxComposeCompiler").get().toString()
        }
    }
}