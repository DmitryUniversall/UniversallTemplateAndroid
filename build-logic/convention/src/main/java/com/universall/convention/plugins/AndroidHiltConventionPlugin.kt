package com.universall.convention.plugins

import com.universall.convention.utils.implementation
import com.universall.convention.utils.ksp
import com.universall.convention.utils.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidHiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        plugins.withId("com.android.application") { pluginSetup() }
        plugins.withId("com.android.library") { pluginSetup() }
    }

    private fun Project.pluginSetup() {
        setup()
        setupDependencies()
    }

    private fun Project.setup() {
        pluginManager.apply(libs.findPlugin("ksp").get().get().pluginId)
        pluginManager.apply(libs.findPlugin("hilt-android").get().get().pluginId)
    }

    private fun Project.setupDependencies() {
        dependencies {
            ksp(libs.findLibrary("hilt-compiler").get())
            implementation(libs.findLibrary("hilt-android").get())
            implementation(libs.findLibrary("hilt-navigation-compose").get())
        }
    }
}
