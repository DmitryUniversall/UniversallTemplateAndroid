package com.universall.auth_impl.data.dto

import com.universall.auth_api.domain.entities.Authority
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class AuthorityDTO(
    @SerialName("id")
    val id: Long,

    @SerialName("name")
    val name: String
) {
    fun toEntity(): Authority = Authority(
        id = id,
        name = name
    )
}
