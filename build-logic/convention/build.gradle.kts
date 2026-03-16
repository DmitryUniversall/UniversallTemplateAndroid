plugins {
    `kotlin-dsl`
}

repositories {
    google()
    gradlePluginPortal()
    mavenCentral()
}

group = "com.universall.buildlogic"

kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21
    }
}

dependencies {
    compileOnly(libs.gradle.plugin.android)
}

gradlePlugin {
    plugins {
        register("ConventionAndroidCore") {
            id = "convention.android.core"
            implementationClass = "com.universall.convention.plugins.AndroidCoreConventionPlugin"
        }

        register("ConventionAndroidLibrary") {
            id = "convention.android.library"
            implementationClass = "com.universall.convention.plugins.AndroidLibraryConventionPlugin"
        }

        register("ConventionAndroidCompose") {
            id = "convention.android.compose"
            implementationClass = "com.universall.convention.plugins.AndroidComposeConventionPlugin"
        }

        register("ConventionAndroidHilt") {
            id = "convention.android.hilt"
            implementationClass = "com.universall.convention.plugins.AndroidHiltConventionPlugin"
        }

        register("ConventionNetworkKtor") {
            id = "convention.network.ktor"
            implementationClass = "com.universall.convention.plugins.NetworkKtorConventionPlugin"
        }
    }
}
