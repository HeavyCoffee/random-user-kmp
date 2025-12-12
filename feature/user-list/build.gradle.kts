plugins {
    alias(libs.plugins.buildlogic.kmp.library)
    alias(libs.plugins.buildlogic.compose)
    alias(libs.plugins.buildlogic.kmp.di)
}
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.uiKit)
            implementation(projects.core.database)
            implementation(projects.core.feature)
        }
    }
}

dependencies {
    implementation(projects.core.uiKit)
}