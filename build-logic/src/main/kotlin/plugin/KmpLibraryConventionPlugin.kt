package plugin

import com.android.build.api.dsl.LibraryExtension
import ext.configureAndroid
import ext.configureMultiplatform
import ext.getAndroidSdkVer
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KmpLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        apply(plugin = "org.jetbrains.kotlin.multiplatform")
        apply(plugin = "com.android.library")


        extensions.configure<KotlinMultiplatformExtension> {
            configureMultiplatform(this)
        }

        extensions.configure<LibraryExtension> {
            configureAndroid(extension = this, androidSdk = getAndroidSdkVer())
        }
    }
}