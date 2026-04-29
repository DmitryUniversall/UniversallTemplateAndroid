package com.universall.appcore.appcore.dto

import com.universall.core.entiites.ApiMessage
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement

@Serializable
data class ApiMessageDTO(
    @SerialName("ok")
    val ok: Boolean,

    @SerialName("appCode")
    val appCode: Int,

    @SerialName("message")
    val message: String,

    @SerialName("data")
    val data: JsonElement? = null
) {
    fun <T : Any> toEntity(json: Json, deserializer: DeserializationStrategy<T>): ApiMessage<T> = ApiMessage(
        ok = ok,
        appCode = appCode,
        message = message,
        data = data?.let { json.decodeFromJsonElement(deserializer, data) }
    )
}
