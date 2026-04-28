package com.universall.convention.plugins

import com.universall.convention.utils.androidTestImplementation
import com.universall.convention.utils.implementation
import com.universall.convention.utils.libs
import com.universall.convention.utils.testImplementation

private fun configureDependencies() {
    dependencies {
        implementation(libs.findLibrary("androidx-core-ktx").get())
        implementation(libs.findLibrary("androidx-lifecycle-runtime-ktx").get())

        testImplementation(libs.findLibrary("junit").get())
        androidTestImplementation(libs.findLibrary("androidx-junit").get())
        androidTestImplementation(libs.findLibrary("androidx-espresso-core").get())
    }
}

pluginManager.withPlugin("com.android.application") { configureDependencies() }
pluginManager.withPlugin("com.android.library") { configureDependencies() }
