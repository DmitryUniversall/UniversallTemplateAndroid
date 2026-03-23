package com.universall.auth_impl.data.dto.refresh

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class RefreshRequestDTO(
    @SerialName("refreshToken")
    val refreshToken: String,

    @SerialName("clientName")
    val clientName: String
)
