package com.universall.appcore.base.network.api

import com.universall.appcore.base.network.api.attributes.ApiCallAttributeKey
import com.universall.appcore.base.network.api.attributes.ApiContextAttributes
import io.ktor.client.request.HttpRequestBuilder

data class ApiRequest(
    val attributes: ApiContextAttributes = ApiContextAttributes(),
    val buildRequest: HttpRequestBuilder.() -> Unit = {}
) {
    class Builder {
        internal val attributes: ApiContextAttributes = ApiContextAttributes()
        internal var buildRequest: HttpRequestBuilder.() -> Unit = {}

        fun http(block: HttpRequestBuilder.() -> Unit) {
            buildRequest = block
        }

        fun <T : Any> attribute(key: ApiCallAttributeKey<T>, value: T) {
            attributes.put(key, value)
        }

        fun <T : Any> removeAttribute(key: ApiCallAttributeKey<T>) {
            attributes.remove(key)
        }
    }

    fun modifyRequest(block: HttpRequestBuilder.() -> Unit): ApiRequest {
        return this.copy(
            buildRequest = {
                this@ApiRequest.buildRequest(this)
                block()
            }
        )
    }
}
