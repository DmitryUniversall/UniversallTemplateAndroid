package com.universall.auth_api.domain.entities

import com.universall.core.collections.annotations.ConventionImmutable

@ConventionImmutable
data class AuthContext(
    val session: AuthSession,
    val user: User,
    val accessInfo: AccessInfo
)
