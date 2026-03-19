package com.universall.auth_impl.domain.utils.network

import com.universall.appcore.network.impl.api.ApiClient
import com.universall.appcore.network.impl.api.base.ApiResponseContext
import com.universall.appcore.network.impl.api.base.middleware.ApiClientMiddlewareChain
import com.universall.appcore.network.impl.api.middleware.AppCodeProcessingMiddleware
import com.universall.appcore.network.impl.api.middleware.KtorExecuteMiddleware
import com.universall.auth_api.domain.usecases.GetTokenPairUseCase
import com.universall.auth_api.domain.usecases.RefreshUseCase
import com.universall.auth_impl.domain.utils.network.middleware.AuthMiddleware
import com.universall.auth_impl.domain.utils.network.middleware.AutoRefreshTokensMiddleware
import io.ktor.client.HttpClient
import kotlinx.serialization.json.Json

class AuthenticatedApiClientImpl(
    httpClient: HttpClient,
    json: Json,
    retryCount: Int,
    private val getTokenPairUseCase: GetTokenPairUseCase,
    private val refreshUseCase: RefreshUseCase
) : ApiClient(httpClient, json, retryCount) {
    override fun <T> setupMiddlewareChain(): ApiClientMiddlewareChain.Builder<ApiResponseContext<T>> {
        return super.setupMiddlewareChain<T>()
            .insertAfter(KtorExecuteMiddleware::class.java, AuthMiddleware(getTokenPairUseCase))
            .insertAfter(AppCodeProcessingMiddleware::class.java, AutoRefreshTokensMiddleware(refreshUseCase))
    }
}
