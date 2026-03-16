package com.universall.auth_impl.data.dto

import com.universall.auth_api.domain.entities.Role
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
class RoleDTO(
    @SerialName("id")
    val id: Long,

    @SerialName("name")
    val name: String
) {
    fun toEntity(): Role {
        return Role(
            id = id,
            name = name
        )
    }
}
