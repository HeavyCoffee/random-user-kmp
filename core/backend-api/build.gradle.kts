plugins {
    alias(libs.plugins.buildlogic.kmp.library)
    alias(libs.plugins.kotlin.serializationPlugin)
    alias(libs.plugins.buildlogic.kmp.di)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.network)
        }
    }
}