import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.jetbrains.kotlin.js.inline.clean.removeUnusedImports

plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.jetbrainsCompose) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.nativecoroutines) apply false
    alias(libs.plugins.sqldelight) apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.spotless)
    alias(libs.plugins.kotlin.dokka)
}
subprojects {
    apply(plugin = rootProject.libs.plugins.kotlin.dokka.get().pluginId)
    apply(plugin = rootProject.libs.plugins.detekt.get().pluginId)
    apply(plugin = rootProject.libs.plugins.spotless.get().pluginId)
    configure<DetektExtension> {
        buildUponDefaultConfig = true
        allRules = false
        config.setFrom("$rootDir/detekt.yml")
    }
}

spotless {
    kotlin {
        target("**/*.kt")
        ktlint()
            .editorConfigOverride(
                mapOf(
                    "indent_size" to 4,
                    "indent_style" to "space",
                    "ij_kotlin_imports_layout" to "*,java.**,javax.**,kotlin.**,kotlinx.**,^",
                    "ij_kotlin_allow_trailing_comma_on_call_site" to "true",
                    "ij_kotlin_allow_trailing_comma" to "true",
                    "ktlint_function_naming_ignore_when_annotated_with" to "Composable",
                    "ktlint_code_style" to "android_studio",
                )
            )
        trimTrailingWhitespace()
        endWithNewline()
    }
}
