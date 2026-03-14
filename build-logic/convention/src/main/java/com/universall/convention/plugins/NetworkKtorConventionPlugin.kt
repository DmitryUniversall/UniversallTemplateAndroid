package com.universall.convention.plugins

import com.universall.convention.utils.implementation
import com.universall.convention.utils.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies


class NetworkKtorConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        plugins.withId("com.android.application") { pluginSetup() }
        plugins.withId("com.android.library") { pluginSetup() }
    }

    private fun Project.pluginSetup() {
        setup()
        setupDependencies()
    }

    private fun Project.setup() {
        pluginManager.apply(libs.findPlugin("kotlin-serialization").get().get().pluginId)
    }

    private fun Project.setupDependencies() {
        dependencies {
            implementation(libs.findLibrary("ktor-client").get())
            implementation(libs.findLibrary("ktor-client-cio").get())
            implementation(libs.findLibrary("ktor-client-logging").get())
            implementation(libs.findLibrary("ktor-client-content").get())
            implementation(libs.findLibrary("ktor-client-serialization").get())

            implementation(libs.findLibrary("kotlinx-serialization-json").get())
        }
    }
}
