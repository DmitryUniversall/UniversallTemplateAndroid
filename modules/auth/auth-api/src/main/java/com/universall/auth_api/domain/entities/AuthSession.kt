package com.universall.auth_api.domain.entities

import com.universall.core.collections.annotations.ConventionImmutable
import java.time.LocalDateTime

@ConventionImmutable
data class AuthSession(
    val uuid: String,
    val name: String,
    val userId: Long,
    val isActive: Boolean = true,
    val createdAt: LocalDateTime,
    val lastUsedAt: LocalDateTime,
    val expiresAt: LocalDateTime
)
