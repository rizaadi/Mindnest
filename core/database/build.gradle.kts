plugins {
    alias(libs.plugins.mindnest.android.library)
    alias(libs.plugins.mindnest.android.room)
    alias(libs.plugins.mindnest.hilt)
}

android {
    namespace = "com.zephysus.mindnest.core.database"
}

dependencies {
    api(projects.core.model)

    implementation(libs.kotlinx.datetime)
}