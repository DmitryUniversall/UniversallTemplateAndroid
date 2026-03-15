package com.universall.auth_api.domain.entities

import java.time.LocalDateTime

data class User(
    val id: Long,
    val username: String,
    val createdAt: LocalDateTime
)
