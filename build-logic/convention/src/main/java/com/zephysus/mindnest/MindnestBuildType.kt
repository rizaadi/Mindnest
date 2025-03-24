package com.zephysus.mindnest

/**
 * This is shared between :app and :benchmarks module to provide configurations type safety.
 */
enum class MindnestBuildType(val applicationIdSuffix: String? = null) {
    DEBUG(".debug"),
    RELEASE,
}
