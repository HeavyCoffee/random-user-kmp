plugins {
    alias(libs.plugins.buildlogic.kmp.library)
    alias(libs.plugins.buildlogic.compose)
    alias(libs.plugins.buildlogic.kmp.di)
}

kotlin {
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).apply {
        forEach { iosTarget ->
            iosTarget.binaries.framework {
                baseName = "Shared"
                isStatic = true
            }
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.decompose)
            implementation(libs.essenty.lifecylce)
            implementation(libs.decompose.composeExt)
            implementation(projects.core.uiKit)
            implementation(projects.core.network)
            implementation(projects.core.backendApi)
            implementation(projects.core.database)
            implementation(projects.feature.newUser)
            implementation(projects.feature.userInfo)
            implementation(projects.feature.userList)
            implementation(projects.navigation)
        }
    }
}