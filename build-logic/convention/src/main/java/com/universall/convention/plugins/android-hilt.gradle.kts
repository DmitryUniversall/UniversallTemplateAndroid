package com.universall.convention.plugins

import com.universall.convention.utils.implementation
import com.universall.convention.utils.ksp
import com.universall.convention.utils.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

private fun configureDependencies() {
    dependencies {
        ksp(libs.findLibrary("hilt-compiler").get())
        implementation(libs.findLibrary("hilt-android").get())
        implementation(libs.findLibrary("hilt-navigation-compose").get())
    }
}

private fun configurePlugins() {
    pluginManager.apply("com.google.devtools.ksp")
    pluginManager.apply("com.google.dagger.hilt.android")
}

private fun configure() {
    configurePlugins()
    configureDependencies()
}

pluginManager.withPlugin("com.android.application") { configure() }
pluginManager.withPlugin("com.android.library") { configure() }
