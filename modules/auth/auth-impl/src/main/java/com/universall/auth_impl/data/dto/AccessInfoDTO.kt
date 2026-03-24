package com.universall.auth_impl.data.dto

import com.universall.auth_api.domain.entities.AccessInfo
import kotlinx.collections.immutable.toPersistentList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class AccessInfoDTO(
    @SerialName("authorities")
    val authorities: List<AuthorityDTO> = emptyList(),

    @SerialName("roles")
    val roles: List<RoleDTO> = emptyList()
) {
    fun toEntity(): AccessInfo = AccessInfo(
        authorities = authorities.map { it.toEntity() }.toPersistentList(),
        roles = roles.map { it.toEntity() }.toPersistentList()
    )
}
