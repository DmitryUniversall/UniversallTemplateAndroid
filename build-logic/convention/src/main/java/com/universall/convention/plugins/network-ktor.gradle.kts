package com.universall.convention.plugins

import com.universall.convention.utils.implementation
import com.universall.convention.utils.libs
import org.gradle.kotlin.dsl.dependencies

plugins {
    id("org.jetbrains.kotlin.plugin.serialization")
}

dependencies {
    implementation(libs.findLibrary("ktor-client").get())
    implementation(libs.findLibrary("ktor-client-cio").get())
    implementation(libs.findLibrary("ktor-client-logging").get())
    implementation(libs.findLibrary("ktor-client-content").get())
    implementation(libs.findLibrary("ktor-client-serialization").get())

    implementation(libs.findLibrary("kotlinx-serialization-json").get())
}
