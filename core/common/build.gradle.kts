plugins {
    alias(libs.plugins.mindnest.jvm.library)
    alias(libs.plugins.mindnest.hilt)
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    testImplementation(libs.kotlinx.coroutines.test)
}