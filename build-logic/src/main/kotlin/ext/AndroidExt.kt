package ext

import AndroidConfig
import AndroidSDK
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project

internal fun Project.configureAndroid(
    extension: CommonExtension<*, *, *, *, *, *>,
    androidSdk: AndroidSDK
) = extension.apply {
    val projectNameSpace = projectDir
        .relativeTo(rootDir)
        .invariantSeparatorsPath
        .replace(Regex("[/-]")) { if (it.value == "/") "." else "" }

    this.namespace = "${AndroidConfig.BASE_NAME_SPACE}.$projectNameSpace"
    compileSdk = androidSdk.compile

    defaultConfig {
        minSdk = androidSdk.min
    }

    compileOptions {
        sourceCompatibility = AndroidConfig.JAVA_VERSION
        targetCompatibility = AndroidConfig.JAVA_VERSION
    }
}

internal fun Project.getAndroidSdkVer(): AndroidSDK = getLibs().run {
    AndroidSDK(
        min = findVersion("android-minSdk").get().requiredVersion.toInt(),
        target = findVersion("android-targetSdk").get().requiredVersion.toInt(),
        compile = findVersion("android-compileSdk").get().requiredVersion.toInt()
    )
}