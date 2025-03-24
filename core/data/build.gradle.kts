plugins {
    alias(libs.plugins.mindnest.android.library)
    alias(libs.plugins.mindnest.hilt)
}

android {
    namespace = "com.zephysus.mindnest.core.data"
}

dependencies {
    api(projects.core.model)
    api(projects.core.database)
    api(projects.core.common)


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}