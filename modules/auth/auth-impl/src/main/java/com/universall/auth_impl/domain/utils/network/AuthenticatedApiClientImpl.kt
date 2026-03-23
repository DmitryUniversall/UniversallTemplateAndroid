package com.universall.auth_impl.domain.utils.network

import com.universall.appcore.network.api.ApiClient
import com.universall.appcore.network.api.base.ApiResponseContext
import com.universall.appcore.network.api.base.middleware.ApiClientMiddlewareChain
import com.universall.appcore.network.api.middleware.AppCodeProcessingMiddleware
import com.universall.appcore.network.api.middleware.KtorExecuteMiddleware
import com.universall.auth_api.domain.usecases.GetTokenPairUseCase
import com.universall.auth_api.domain.usecases.RefreshUseCase
import com.universall.auth_impl.domain.utils.network.middleware.AuthMiddleware
import com.universall.auth_impl.domain.utils.network.middleware.AutoRefreshTokensMiddleware
import io.ktor.client.HttpClient
import kotlinx.serialization.json.Json

internal class AuthenticatedApiClientImpl(
    httpClient: HttpClient,
    json: Json,
    private val getTokenPairUseCase: GetTokenPairUseCase,
    private val refreshUseCase: RefreshUseCase
) : ApiClient(httpClient, json) {
    override fun <T> setupMiddlewareChain(): ApiClientMiddlewareChain.Builder<ApiResponseContext<T>> {
        return super.setupMiddlewareChain<T>()
            .insertAfter(KtorExecuteMiddleware::class.java, AuthMiddleware(getTokenPairUseCase))
            .insertAfter(AppCodeProcessingMiddleware::class.java, AutoRefreshTokensMiddleware(refreshUseCase))
    }
}
