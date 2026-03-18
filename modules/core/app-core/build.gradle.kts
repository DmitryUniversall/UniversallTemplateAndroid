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
            buildConfigField("String", "API_URL", findLocalProperty("PRODUCTION_API_URL"))
            buildConfigField("int", "NETWORK_REQUEST_RETRY_COUNT", findLocalProperty("PRODUCTION_NETWORK_REQUEST_RETRY_COUNT"))
        }

        debug {
            buildConfigField("String", "API_URL", findLocalProperty("DEBUG_API_URL"))
            buildConfigField("int", "NETWORK_REQUEST_RETRY_COUNT", findLocalProperty("DEBUG_NETWORK_REQUEST_RETRY_COUNT"))
        }
    }
}

dependencies {
    implementation(project(":modules:core:core"))

    implementation(libs.androidx.compose.ui.text.google.fonts)
    implementation(libs.google.crypto.tink)
}
