package com.universall.appcore.network.api.base

import com.universall.core.entities.ApiResponse
import io.ktor.client.statement.HttpResponse

data class ApiResponseContext<T>(
    val httpResponse: HttpResponse,
    val apiResponse: ApiResponse<T>
)
