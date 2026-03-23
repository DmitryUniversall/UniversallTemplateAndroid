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
    namespace = "com.universall.init_impl"
}

dependencies {
    api(project(":modules:init:init-api"))

    implementation(project(":modules:core:core"))
    implementation(project(":modules:core:app-core"))

    implementation(project(":modules:auth:auth-api"))

    implementation(project(":modules:server-tools:server-tools-api"))
}
