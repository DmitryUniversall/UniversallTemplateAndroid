package com.universall.appcore.network.api.middleware.generics

import com.universall.appcore.entities.ApiResponse
import com.universall.appcore.network.api.ApiRequestContext
import com.universall.appcore.network.api.ApiResponseContext
import com.universall.appcore.network.api.middleware.ApiClientMiddleware
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

        val apiResponseJson = json.decodeFromString<ApiResponse<JsonElement>>(text)

        val data: T? = apiResponseJson.data?.let {
            val serializer = context.getFromMeta<KSerializer<T>>("serializer") ?: throw IllegalStateException("Serializer for type T not found in context")
            json.decodeFromJsonElement(serializer, apiResponseJson.data)
        }

        return ApiResponseContext(
            httpResponse = httpResponse,
            apiResponse = ApiResponse(
                ok = apiResponseJson.ok,
                appCode = apiResponseJson.appCode,
                message = apiResponseJson.message,
                data = data
            )
        )
    }
}
