package com.universall.auth_api.domain.entities

import com.universall.core.collections.annotations.ConventionImmutable
import java.time.LocalDateTime

@ConventionImmutable
data class User(
    val id: Long,
    val login: String,
    val createdAt: LocalDateTime
)
