package com.universall.auth_impl.data.dto

import com.universall.auth_api.domain.entities.AuthContext
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class AuthContextDTO(
    @SerialName("session")
    val session: AuthSessionPrivateDTO,

    @SerialName("user")
    val user: UserPrivateDTO,

    @SerialName("accessInfo")
    val accessInfo: AccessInfoDTO
) {
    fun toEntity(): AuthContext = AuthContext(
        session = session.toEntity(),
        user = user.toEntity(),
        accessInfo = accessInfo.toEntity()
    )
}
