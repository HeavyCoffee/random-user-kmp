plugins {
    alias(libs.plugins.buildlogic.kmp.library)
    alias(libs.plugins.buildlogic.kmp.di)
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
        commonMain.dependencies {
            api(libs.ktor.core)
            implementation(libs.ktor.client.serialization)
            implementation(libs.ktor.client.contentNegotiation)
            implementation(libs.ktor.client.kotlinxJson)
            implementation(libs.ktor.client.auth)
            implementation(libs.ktor.client.logging)
            api(libs.kotlinx.serialization.json)
        }
    }
}