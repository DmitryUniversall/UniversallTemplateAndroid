package com.universall.auth_impl.data.dto.login

import com.universall.auth_impl.data.dto.AccessInfoDTO
import com.universall.auth_impl.data.dto.AuthSessionPrivateDTO
import com.universall.auth_impl.data.dto.AuthTokenPairDTO
import com.universall.auth_impl.data.dto.UserPrivateDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDTO(
    @SerialName("user")
    val user: UserPrivateDTO,

    @SerialName("session")
    val session: AuthSessionPrivateDTO,

    @SerialName("tokens")
    val tokens: AuthTokenPairDTO,

    @SerialName("accessInfo")
    val accessInfo: AccessInfoDTO
)
