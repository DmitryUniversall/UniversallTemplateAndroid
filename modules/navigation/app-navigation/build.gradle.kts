plugins {
    alias(libs.plugins.android.library)

    // Convention
    alias(libs.plugins.convention.android.core)
    alias(libs.plugins.convention.android.library)
}

android {
    namespace = "com.universall.app_navigation"
}

dependencies {
    implementation(project(":modules:core:app-core"))
}
