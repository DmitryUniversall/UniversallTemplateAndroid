package com.universall.auth_impl.data.dto

import com.universall.auth_api.domain.entities.LocalAuthInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class LocalAuthInfoDTO(
    @SerialName("accessToken")
    val accessToken: String,

    @SerialName("refreshToken")
    val refreshToken: String
) {
    fun toEntity(): LocalAuthInfo = LocalAuthInfo(
        accessToken = accessToken,
        refreshToken = refreshToken
    )

    companion object {
        fun fromEntity(entity: LocalAuthInfo): LocalAuthInfoDTO = LocalAuthInfoDTO(
            accessToken = entity.accessToken,
            refreshToken = entity.refreshToken
        )
    }
}
