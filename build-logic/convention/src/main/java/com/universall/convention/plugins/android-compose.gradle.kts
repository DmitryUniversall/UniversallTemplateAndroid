package com.universall.convention.plugins

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import com.universall.convention.utils.androidTestImplementation
import com.universall.convention.utils.debugImplementation
import com.universall.convention.utils.implementation

private fun configureComposeDependencies() {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

    val composeBom = libs.findLibrary("androidx-compose-bom").get()

    dependencies {
        implementation(platform(composeBom))
        implementation(libs.findLibrary("androidx-compose-ui").get())
        implementation(libs.findLibrary("androidx-compose-material3").get())
        implementation(libs.findLibrary("androidx-compose-ui-graphics").get())
        implementation(libs.findLibrary("androidx-compose-ui-tooling-preview").get())

        androidTestImplementation(platform(composeBom))

        debugImplementation(libs.findLibrary("androidx-compose-ui-tooling").get())
        debugImplementation(libs.findLibrary("androidx-compose-ui-test-manifest").get())
    }
}

private fun configureLibraryCompose() {
    pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

    extensions.configure<LibraryExtension>("android") {
        buildFeatures.compose = true
    }

    configureComposeDependencies()
}

private fun configureApplicationCompose() {
    pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

    extensions.configure<ApplicationExtension>("android") {
        buildFeatures.compose = true
    }

    configureComposeDependencies()
}

pluginManager.withPlugin("com.android.application") { configureApplicationCompose() }
pluginManager.withPlugin("com.android.library") { configureLibraryCompose() }
