plugins {
    // Convention
    alias(libs.plugins.convention.android.core)
    alias(libs.plugins.convention.android.library)
    alias(libs.plugins.convention.android.compose)
    alias(libs.plugins.convention.android.hilt)
    alias(libs.plugins.convention.android.ktor)
}

android {
    namespace = "com.universall.core"
}

dependencies { }
