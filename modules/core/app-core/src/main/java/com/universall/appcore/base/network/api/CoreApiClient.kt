package com.universall.appcore.base.network.api

import com.universall.appcore.base.network.api.interceptors.ApiCallInterceptor

class CoreApiClient(
    val transport: ApiCallTransport,
    val interceptors: List<ApiCallInterceptor>
) : ApiClient {
    val apiCall = ApiCall(transport = transport, interceptors = interceptors)

    override suspend fun <T> request(transformer: ApiResponseTransformer<T>, configure: ApiRequest.Builder.() -> Unit): T {
        val builder = ApiRequest.Builder().apply(block = configure)

        val response = apiCall.execute(
            request = ApiRequest(
                attributes = builder.attributes,
                buildRequest = builder.buildRequest
            )
        )

        return transformer.transform(response)
    }
}
