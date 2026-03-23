package com.universall.auth_impl.data.dto.get_active_sessions

import com.universall.auth_impl.data.dto.AuthSessionPrivateDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class GetActiveSessionsResponseDTO(
    @SerialName("sessions")
    val sessions: List<AuthSessionPrivateDTO>
)
