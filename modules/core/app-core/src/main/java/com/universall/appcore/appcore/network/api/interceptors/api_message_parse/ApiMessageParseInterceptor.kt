package com.universall.appcore.appcore.network.api.interceptors.api_message_parse

import com.universall.appcore.appcore.dto.ApiMessageDTO
import com.universall.appcore.appcore.network.api.interceptors.response_body_text.ResponseBodyTextApiCallFeature
import com.universall.appcore.base.network.api.ApiRequest
import com.universall.appcore.base.network.api.ApiResponse
import com.universall.appcore.base.network.api.interceptors.ApiCallInterceptor
import jakarta.inject.Inject
import kotlinx.serialization.json.Json

class ApiMessageParseInterceptor @Inject constructor(
    private val json: Json
) : ApiCallInterceptor {
    override suspend fun process(
        input: ApiRequest,
        next: suspend (ApiRequest) -> ApiResponse
    ): ApiResponse {
        val response = next(input)

        val config = input.attributes.get(ApiMessageParseApiCallFeature.Config) ?: return response
        if (!config.enabled) return response

        val bodyText = response.attributes.get(ResponseBodyTextApiCallFeature.BodyTextValue) ?: error("Api message parsing requires body text attribute")

        val dto = json.decodeFromString(
            deserializer = ApiMessageDTO.serializer(),
            string = bodyText
        )

        response.attributes.put(ApiMessageParseApiCallFeature.MessageDTO, dto)

        return response
    }
}
