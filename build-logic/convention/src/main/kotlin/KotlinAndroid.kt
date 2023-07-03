import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension

/**
 * Configure base Kotlin with Android options
 */
internal fun Project.configureKotlinAndroid(
	commonExtension: CommonExtension<*, *, *, *, *>,
	applyKotlinOptions: Boolean = false,
) {
	commonExtension.apply {
		compileSdk = 33

		defaultConfig {
			minSdk = 26

			testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		}

		compileOptions {
			sourceCompatibility = JavaVersion.VERSION_17
			targetCompatibility = JavaVersion.VERSION_17
			isCoreLibraryDesugaringEnabled = true
		}

		configure<KotlinProjectExtension> {
			sourceSets.all {
				languageSettings {
					optIn("kotlin.RequiresOptIn")
					optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
					optIn("kotlinx.coroutines.FlowPreview")
					optIn("kotlin.Experimental")
					optIn("kotlinx.serialization.ExperimentalSerializationApi")
				}
			}
		}

		if (applyKotlinOptions) {
			configure<KotlinJvmOptions> {
				// Treat all Kotlin warnings as errors (disabled by default)
				allWarningsAsErrors = properties["warningsAsErrors"] as? Boolean ?: false

				freeCompilerArgs = freeCompilerArgs + listOf(
					"-opt-in=kotlin.RequiresOptIn",
					// Enable experimental coroutines APIs, including Flow
					"-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
					"-opt-in=kotlinx.coroutines.FlowPreview",
					"-opt-in=kotlin.Experimental",
					// Enable experimental kotlinx serialization APIs
					"-opt-in=kotlinx.serialization.ExperimentalSerializationApi",
				)

				jvmTarget = JavaVersion.VERSION_17.toString()
			}
		}
	}

	val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

	dependencies {
		add("coreLibraryDesugaring", libs.findLibrary("android.desugarJdkLibs").get())
	}
}
