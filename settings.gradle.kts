@file:Suppress("UnstableApiUsage")

pluginManagement {
    includeBuild("build-logic")

    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "UniversallTemplate"

include(":app")

// Core
include(":modules:core:core")
include(":modules:core:app-core")

// Navigation
include(":modules:navigation:navigation-impl")

// Server tools
include(":modules:server-tools:server-tools-api")
include(":modules:server-tools:server-tools-impl")

// Init
include(":modules:init:init-impl")

// Auth
include(":modules:auth:auth-api")
include(":modules:auth:auth-impl")

// Main screen
include(":modules:main:main")
