package ext

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

internal fun Project.getLibs(): VersionCatalog {
    return extensions.getByType<VersionCatalogsExtension>().named("libs")
}
