package utils

import org.gradle.api.Project
import java.util.Properties

fun Project.findLocalProperty(key: String, default: String? = null): String {
    val properties = Properties()
    val file = rootProject.file("local.properties")

    if (file.exists()) {
        file.inputStream().use { properties.load(it) }
    } else {
        if (default != null) return default
        throw IllegalStateException("local.properties file not found in project root")
    }

    return properties.getProperty(key) ?: default
    ?: throw IllegalStateException("Key '$key' was not found in local.properties")
}
