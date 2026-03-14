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
    implementation(project(":core"))

    implementation(libs.androidx.activity.compose)
}
