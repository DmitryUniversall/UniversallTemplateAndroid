package com.universall.core.entities

import com.universall.core.collections.annotations.ConventionImmutable

@ConventionImmutable
data class ApiResponse<@ConventionImmutable T>(
    val ok: Boolean,
    val appCode: Int,
    val message: String,
    val data: T? = null
)
