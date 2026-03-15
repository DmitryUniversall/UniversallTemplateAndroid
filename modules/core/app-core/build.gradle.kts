import utils.findLocalProperty

plugins {
    alias(libs.plugins.android.library)

    // Convention
    alias(libs.plugins.convention.android.core)
    alias(libs.plugins.convention.android.library)
    alias(libs.plugins.convention.android.compose)
    alias(libs.plugins.convention.android.hilt)
    alias(libs.plugins.convention.android.ktor)
}

android {
    namespace = "com.universall.appcore"

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        release {
            val apiUrl = findLocalProperty("PRODUCTION_API_URL")
            buildConfigField("String", "API_URL", apiUrl)
        }

        debug {
            val apiUrl = findLocalProperty("DEBUG_API_URL")
            buildConfigField("String", "API_URL", apiUrl)
        }
    }
}

dependencies {
    implementation(project(":modules:core:core"))

    implementation(libs.androidx.compose.ui.text.google.fonts)
}
