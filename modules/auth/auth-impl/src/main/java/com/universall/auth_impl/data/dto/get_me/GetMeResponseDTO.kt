package com.universall.auth_impl.data.dto.get_me

import com.universall.auth_impl.data.dto.UserPrivateDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class GetMeResponseDTO(
    @SerialName("user")
    val user: UserPrivateDTO
)
