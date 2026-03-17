package com.universall.auth_api.domain.entities

data class LocalAuthInfo(
    val accessToken: String,
    val refreshToken: String
) {
    fun asAuthTokenPair(): AuthTokenPair = AuthTokenPair(
        accessToken = accessToken,
        refreshToken = refreshToken
    )
}
