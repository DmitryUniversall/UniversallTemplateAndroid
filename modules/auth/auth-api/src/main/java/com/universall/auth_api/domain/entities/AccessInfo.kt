package com.universall.auth_api.domain.entities

data class AccessInfo(
    val authorities: List<Authority> = emptyList(),
    val roles: List<Role> = emptyList()
)
