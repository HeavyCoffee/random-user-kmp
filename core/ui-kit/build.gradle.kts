plugins {
    alias(libs.plugins.buildlogic.kmp.library)
    alias(libs.plugins.buildlogic.compose)
}
kotlin {
    sourceSets {
        commonMain.dependencies {
            api(libs.compose.runtime)
            api(libs.compose.foundation)
            api(libs.compose.material3)
            //implementation(libs.common.compose.ui)
            api(libs.compose.resources)
            api(libs.compose.preview)
            implementation(libs.coil.compose)
            implementation(libs.coil.network.ktor)
        }
        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }
}
compose.resources {
    publicResClass = true
    packageOfResClass = "com.heavycoffee.core.uikit.resources"
    generateResClass = always
}

dependencies {
    debugApi(libs.compose.uiTooling)
}