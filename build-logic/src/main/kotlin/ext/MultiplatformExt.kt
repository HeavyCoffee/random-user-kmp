package ext

import AndroidConfig
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureMultiplatform(
    extension: KotlinMultiplatformExtension,
) = extension.apply {
    androidTarget {
        compilerOptions {
            jvmTarget.set(AndroidConfig.JAVA_TARGET)
        }
    }

    iosArm64()
    iosSimulatorArm64()
}