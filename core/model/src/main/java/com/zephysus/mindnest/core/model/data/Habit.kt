package com.zephysus.mindnest.core.model.data

import kotlinx.datetime.Instant


data class Habit(
    val id: String,
    val name: String,
    val frequency: String,
    val streak: Int,
    val lastCompleted: Instant,
    val createdAt: Instant,
)