package com.universall.appcore.entities

data class ApiResponse<T>(
    val ok: Boolean,
    val appCode: Int,
    val message: String,
    val data: T? = null
)
