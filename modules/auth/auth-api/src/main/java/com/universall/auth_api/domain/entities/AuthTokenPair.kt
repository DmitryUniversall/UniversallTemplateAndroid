package com.universall.auth_api.domain.entities

data class AuthTokenPair(
    val accessToken: String,
    val refreshToken: String
)
