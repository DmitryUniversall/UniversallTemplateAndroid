package com.universall.auth_impl.data.dto.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class LoginRequestDTO(
    @SerialName("email")
    val login: String,

    @SerialName("password")
    val password: String,

    @SerialName("clientName")
    val clientName: String
)
