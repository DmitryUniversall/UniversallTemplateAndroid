package com.universall.auth_api.domain.entities

import com.universall.core.collections.annotations.ConventionImmutable

@ConventionImmutable
data class Authority(
    val id: Long,
    val name: String
)
