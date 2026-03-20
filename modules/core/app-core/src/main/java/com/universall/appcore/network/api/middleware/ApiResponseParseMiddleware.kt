package com.universall.appcore.network.api.middleware

import com.universall.appcore.dto.ApiResponseDTO
import com.universall.appcore.network.api.base.ApiRequestContext
import com.universall.appcore.network.api.base.ApiResponseContext
import com.universall.appcore.network.impl.api.base.middleware.ApiClientMiddleware
import com.universall.core.entities.ApiResponse
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement

class ApiResponseParseMiddleware<T>(
    private val json: Json
) : ApiClientMiddleware<HttpRequestBuilder, ApiResponseContext<T>> {
    override suspend fun proceed(
        context: ApiRequestContext,
        next: suspend (HttpRequestBuilder) -> Any?,
        input: HttpRequestBuilder
    ): ApiResponseContext<T> {
        val httpResponse = next(input) as HttpResponse
        val text = httpResponse.bodyAsText()

        val dto = json.decodeFromString<ApiResponseDTO<JsonElement>>(text)

        val skip = context.getFromMeta<Boolean>("skipDataSerialization") ?: false
        val data: T? = if (skip) null else dto.data?.let {
            val serializer = context.getFromMeta<KSerializer<T>>("dataSerializer") ?: throw IllegalStateException("dataSerializer for type T not found in request context")
            json.decodeFromJsonElement(serializer, it)
        }

        return ApiResponseContext(
            httpResponse = httpResponse,
            apiResponse = ApiResponse(
                ok = dto.ok,
                appCode = dto.appCode,
                message = dto.message,
                data = data
            )
        )
    }
}
