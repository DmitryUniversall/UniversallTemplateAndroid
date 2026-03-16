package com.universall.auth_impl.data.dto.get_actual_auth_context

import com.universall.auth_impl.data.dto.AuthContextDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class GetCurrentAuthContextResponseDTO(
    @SerialName("context")
    val context: AuthContextDTO
)
