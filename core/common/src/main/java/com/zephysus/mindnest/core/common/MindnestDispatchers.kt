package com.zephysus.mindnest.core.common

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val mindnestDispatcher: MindnestDispatchers)

enum class MindnestDispatchers {
    Default,
    IO,
}