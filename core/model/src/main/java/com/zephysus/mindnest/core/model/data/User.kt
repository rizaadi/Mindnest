package com.zephysus.mindnest.core.model.data

import kotlinx.datetime.Instant

data class User(
    val id: String,
    val name: String,
    val email: String,
    val createdAt: Instant,
)