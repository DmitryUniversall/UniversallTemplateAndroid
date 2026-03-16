package com.universall.auth_impl.data.dto

import com.universall.auth_api.domain.entities.User
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime


@Serializable
data class UserPrivateDTO(
    @SerialName("id")
    val id: Long,

    @SerialName("email")
    val login: String,

    @Contextual
    @SerialName("createdAt")
    val createdAt: LocalDateTime,
) {
    fun toEntity(): User = User(
        id = id,
        login = login,
        createdAt = createdAt
    )
}
