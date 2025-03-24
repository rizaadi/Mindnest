plugins {
    alias(libs.plugins.mindnest.android.feature)
    alias(libs.plugins.mindnest.android.library.compose)
}

android {
    namespace = "com.zephysus.mindnest.feature.habit"
}

dependencies {
    implementation(projects.core.data)

    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.material3)

//    implementation(libs.androidx.core.ktx)
//    implementation(libs.androidx.appcompat)
//    implementation(libs.material)
//    testImplementation(libs.junit)
//    androidTestImplementation(libs.androidx.junit)
//    androidTestImplementation(libs.androidx.espresso.core)
}