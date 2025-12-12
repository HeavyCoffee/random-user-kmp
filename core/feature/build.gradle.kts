plugins {
    alias(libs.plugins.buildlogic.kmp.library)
}
kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.lib.stateReducer)
            api(libs.decompose)
            api(libs.decompose.composeExt)
            implementation(libs.kotlinx.coroutines.core)
        }
    }
}