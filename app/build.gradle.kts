plugins {
    alias(libs.plugins.android.application)

    // Convention
    alias(libs.plugins.convention.android.core)
    alias(libs.plugins.convention.android.compose)
    alias(libs.plugins.convention.android.hilt)
}

android {
    namespace = "com.universall.template"

    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.universall.template"
        minSdk = 31
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(project(":modules:core:core"))
    implementation(project(":modules:core:app-core"))

    implementation(project(":modules:init:init-api"))
    implementation(project(":modules:init:init-impl"))

    implementation(project(":modules:auth:auth-api"))
    implementation(project(":modules:auth:auth-impl"))

    implementation(project(":modules:server-tools:server-tools-api"))
    implementation(project(":modules:server-tools:server-tools-impl"))

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.ui.text.google.fonts)
    implementation(libs.phosphor.icon)
    implementation(libs.google.crypto.tink)

    implementation(libs.slf4j.api)
    implementation(libs.logback.android)
}
