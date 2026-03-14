package com.universall.convention.utils

import org.gradle.api.artifacts.dsl.DependencyHandler

private object Type {
    const val IMPLEMENTATION = "implementation"
    const val TEST_IMPLEMENTATION = "testImplementation"
    const val ANDROID_TEST_IMPLEMENTATION = "androidTestImplementation"
    const val API = "api"
    const val KAPT = "kapt"
    const val KSP = "ksp"
    const val DEBUG_IMPLEMENTATION = "debugImplementation"
}

data class Dependency(
    val name: String,
    val version: String? = null
) {
    val fullPath get() = version?.let { "$name:$version" } ?: name
}

fun DependencyHandler.implementation(dependency: Dependency) {
    add(Type.IMPLEMENTATION, dependency.fullPath)
}

fun DependencyHandler.implementation(dependency: Any) {
    add(Type.IMPLEMENTATION, dependency)
}

fun DependencyHandler.testImplementation(dependency: Dependency) {
    add(Type.TEST_IMPLEMENTATION, dependency.fullPath)
}

fun DependencyHandler.testImplementation(dependency: Any) {
    add(Type.TEST_IMPLEMENTATION, dependency)
}

fun DependencyHandler.androidTestImplementation(dependency: Dependency) {
    add(Type.ANDROID_TEST_IMPLEMENTATION, dependency.fullPath)
}

fun DependencyHandler.androidTestImplementation(dependency: Any) {
    add(Type.ANDROID_TEST_IMPLEMENTATION, dependency)
}

fun DependencyHandler.api(dependency: Dependency) {
    add(Type.API, dependency.fullPath)
}

fun DependencyHandler.api(dependency: Any) {
    add(Type.API, dependency)
}

fun DependencyHandler.kapt(dependency: Dependency) {
    add(Type.KAPT, dependency.fullPath)
}

fun DependencyHandler.kapt(dependency: Any) {
    add(Type.KAPT, dependency)
}

fun DependencyHandler.ksp(dependency: Dependency) {
    add(Type.KSP, dependency.fullPath)
}

fun DependencyHandler.ksp(dependency: Any) {
    add(Type.KSP, dependency)
}

fun DependencyHandler.debugImplementation(dependency: Dependency) {
    add(Type.DEBUG_IMPLEMENTATION, dependency.fullPath)
}

fun DependencyHandler.debugImplementation(dependency: Any) {
    add(Type.DEBUG_IMPLEMENTATION, dependency)
}
