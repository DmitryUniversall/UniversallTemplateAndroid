package com.universall.core.entiites

import com.universall.core.collections.annotations.ConventionImmutable

@ConventionImmutable
data class ApiMessage<@ConventionImmutable T>(
    val ok: Boolean,
    val appCode: Int,
    val message: String,
    val data: T? = null
)
