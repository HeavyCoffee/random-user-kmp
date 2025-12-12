plugins {
    alias(libs.plugins.buildlogic.kmp.library)
}
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
        }
    }
}