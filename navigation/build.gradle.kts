plugins {
    alias(libs.plugins.buildlogic.kmp.library)
    alias(libs.plugins.buildlogic.kmp.di)
    alias(libs.plugins.buildlogic.compose)
    alias(libs.plugins.kotlin.serializationPlugin)
}
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.uiKit)
            implementation(projects.core.database)
            implementation(projects.core.feature)
            implementation(projects.feature.newUser)
            implementation(projects.feature.userInfo)
            implementation(projects.feature.userList)
        }
    }
}