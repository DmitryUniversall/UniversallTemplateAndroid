package com.universall.auth_impl.data.dto

import com.universall.auth_api.domain.entities.AuthTokenPair
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class AuthTokenPairDTO(
    @SerialName("accessToken")
    val accessToken: String,

    @SerialName("refreshToken")
    val refreshToken: String
) {
    fun toEntity(): AuthTokenPair = AuthTokenPair(
        accessToken = accessToken,
        refreshToken = refreshToken
    )
}
