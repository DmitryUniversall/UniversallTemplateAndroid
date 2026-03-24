package com.universall.auth_api.domain.entities

import com.universall.core.collections.annotations.ConventionImmutable

@ConventionImmutable
data class LocalAuthInfo(
    val accessToken: String,
    val refreshToken: String
) {
    fun asAuthTokenPair(): AuthTokenPair = AuthTokenPair(
        accessToken = accessToken,
        refreshToken = refreshToken
    )
}
