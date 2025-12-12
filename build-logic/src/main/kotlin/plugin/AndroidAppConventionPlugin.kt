package plugin

import AndroidConfig
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import ext.configureAndroid
import ext.getAndroidSdkVer
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

class AndroidAppConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        apply(plugin = "com.android.application")
        apply(plugin = "org.jetbrains.kotlin.android")

        extensions.configure<BaseAppModuleExtension> {
            val androidSdk = getAndroidSdkVer()

            configureAndroid(extension = this, androidSdk = androidSdk)

            defaultConfig {
                applicationId = AndroidConfig.APP_ID
                targetSdk = androidSdk.target
                versionCode = 1
                versionName = AndroidConfig.VERSION_NAME+"($versionCode)"
            }

            packaging {
                resources {
                    excludes += "/META-INF/{AL2.0,LGPL2.1}"
                }
            }

            buildTypes {
                getByName("release") {
                    isMinifyEnabled = false
                }
            }
        }

        extensions.configure<KotlinAndroidProjectExtension> {
            compilerOptions {
                jvmTarget.set(AndroidConfig.JAVA_TARGET)
            }
        }
    }
}