package com.universall.auth_api.domain.entities

import com.universall.core.collections.annotations.ConventionImmutable

@ConventionImmutable
data class Role(
    val id: Long,
    val name: String
)
