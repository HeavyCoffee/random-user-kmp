plugins {
    alias(libs.plugins.buildlogic.android.app)
    alias(libs.plugins.buildlogic.compose)
}

dependencies {
    implementation(projects.shared)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.android)
}