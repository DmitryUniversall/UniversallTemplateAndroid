package com.universall.convention.plugins

import com.universall.convention.utils.androidTestImplementation
import com.universall.convention.utils.implementation
import com.universall.convention.utils.libs
import com.universall.convention.utils.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidCoreConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        plugins.withId("com.android.application") { pluginSetup() }
        plugins.withId("com.android.library") { pluginSetup() }
    }

    private fun Project.pluginSetup() {
        setupDependencies()
    }

    private fun Project.setupDependencies() {
        dependencies {
            implementation(libs.findLibrary("androidx-core-ktx").get())
            implementation(libs.findLibrary("androidx-lifecycle-runtime-ktx").get())

            testImplementation(libs.findLibrary("junit").get())
            androidTestImplementation(libs.findLibrary("androidx-junit").get())
            androidTestImplementation(libs.findLibrary("androidx-espresso-core").get())
        }
    }
}
