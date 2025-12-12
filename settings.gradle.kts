import kotlin.text.replace

rootProject.name = "RandomUser"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

internal fun Settings.includeProjects(
    ignoreDirs: Set<String>
) {
    val excludePatterns = listOf("^[.]".toRegex())

    fun isBuildGradleFile(file: File): Boolean {
        return file.name == "build.gradle.kts"
    }

    fun isAvailableDir(file: File): Boolean {
        return !ignoreDirs.contains(file.name) && file != rootDir
    }

    fun getProjectPath(projectDir: File): String {
        return ":" + projectDir
            .relativeTo(rootDir)
            .invariantSeparatorsPath
            .replace('/', ':')
    }

    rootDir
        .walk()
        .maxDepth(3)
        .filter { file -> isBuildGradleFile(file) }
        .map { it.parentFile }
        .filter { dir ->
            excludePatterns.any { pattern ->
                !pattern.containsMatchIn(dir.name)
            } && isAvailableDir(dir)
        }
        .forEach { dir ->
            include(
                getProjectPath(dir).also {
                    println(it)
                }
            )
        }
}

includeProjects(setOf("build-logic", "iosApp", "build", "gradle"))

includeBuild("build-logic")