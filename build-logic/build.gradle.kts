plugins {
    alias(libs.plugins.kotlin.gradle.jvm)
    `kotlin-dsl`
}

dependencies {
    implementation(libs.plugin.kotlin.gradle)
    implementation(libs.plugin.android)
}

gradlePlugin {
    val pluginPrefix = "build-logic"
    val pluginPath = "plugin"

    plugins {
        register("ComposePlugin") {
            id = "$pluginPrefix.compose"
            implementationClass = "$pluginPath.ComposeConventionPlugin"
        }
        register("KmpLibraryPlugin") {
            id = "$pluginPrefix.kmp.library"
            implementationClass = "$pluginPath.KmpLibraryConventionPlugin"
        }
        register("KmpDiConventionPlugin") {
            id = "$pluginPrefix.kmp.di"
            implementationClass = "$pluginPath.KmpDiConventionPlugin"
        }
        register("AndroidAppConventionPlugin") {
            id = "$pluginPrefix.android.app"
            implementationClass = "$pluginPath.AndroidAppConventionPlugin"
        }
    }
}

group = "build-logic"