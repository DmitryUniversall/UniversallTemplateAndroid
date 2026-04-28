import org.jetbrains.kotlin.gradle.dsl.JvmTarget

fun asGradlePluginRef(plugin: Provider<PluginDependency>) = plugin.map { "${it.pluginId}:${it.pluginId}.gradle.plugin:${it.version}" }!!

plugins {
    `kotlin-dsl`
}

repositories {
    google()
    gradlePluginPortal()
    mavenCentral()
}

group = "com.universall.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_11
    }
}

dependencies {
    implementation(libs.android.gradle.plugin)

    implementation(asGradlePluginRef(libs.plugins.kotlin.compose))
    implementation(asGradlePluginRef(libs.plugins.kotlin.serialization))
}
