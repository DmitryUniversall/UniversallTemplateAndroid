package com.universall.appcore.network.api

import com.universall.appcore.entities.ApiResponse
import io.ktor.client.statement.HttpResponse

data class ApiResponseContext<T>(
    val httpResponse: HttpResponse,
    val apiResponse: ApiResponse<T>
)
