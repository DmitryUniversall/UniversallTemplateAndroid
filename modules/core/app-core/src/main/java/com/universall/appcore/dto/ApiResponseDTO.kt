package com.universall.appcore.dto

import com.universall.core.entities.ApiResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponseDTO<T>(
    @SerialName("ok")
    val ok: Boolean,

    @SerialName("appCode")
    val appCode: Int,

    @SerialName("message")
    val message: String,

    @SerialName("data")
    val data: T? = null
) {
    fun toEntity(): ApiResponse<T> = ApiResponse(
        ok = ok,
        appCode = appCode,
        message = message,
        data = data
    )
}
