package com.universall.auth_api.domain.entities

import com.universall.core.collections.annotations.ConventionImmutable

@ConventionImmutable
data class AuthTokenPair(
    val accessToken: String,
    val refreshToken: String
)
