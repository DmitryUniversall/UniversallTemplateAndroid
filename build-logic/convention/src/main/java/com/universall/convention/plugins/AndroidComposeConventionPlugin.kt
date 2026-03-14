package com.universall.convention.plugins

import com.android.build.api.dsl.CommonExtension
import com.universall.convention.utils.androidTestImplementation
import com.universall.convention.utils.debugImplementation
import com.universall.convention.utils.implementation
import com.universall.convention.utils.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        plugins.withId("com.android.application") { pluginSetup() }
        plugins.withId("com.android.library") { pluginSetup() }
    }

    private fun Project.pluginSetup() {
        setup()
        setupDependencies()
    }

    private fun Project.setup() {
        pluginManager.apply(libs.findPlugin("kotlin-compose").get().get().pluginId)

        val androidExtension = extensions.findByName("android") as? CommonExtension
            ?: error("Android plugin is not applied to the project")

        androidExtension.apply {
            buildFeatures.compose = true
        }
    }

    private fun Project.setupDependencies() {
        dependencies {
            val bom = libs.findLibrary("androidx-compose-bom").get()

            implementation(platform(bom))
            implementation(libs.findLibrary("androidx-compose-ui").get())
            implementation(libs.findLibrary("androidx-compose-material3").get())
            implementation(libs.findLibrary("androidx-compose-ui-graphics").get())
            implementation(libs.findLibrary("androidx-compose-ui-tooling-preview").get())

            androidTestImplementation(platform(bom))

            debugImplementation(libs.findLibrary("androidx-compose-ui-tooling").get())
            debugImplementation(libs.findLibrary("androidx-compose-ui-test-manifest").get())
        }
    }
}
