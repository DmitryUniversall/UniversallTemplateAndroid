package com.universall.auth_api.domain.entities

data class AuthContext(
    val session: AuthSession,
    val user: User,
    val accessInfo: AccessInfo
)
