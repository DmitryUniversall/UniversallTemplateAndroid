package com.universall.auth_impl.domain.utils.network.middleware

import com.universall.appcore.network.exceptions.InvalidAuthHttpException
import com.universall.appcore.network.api.base.ApiRequestContext
import com.universall.appcore.network.api.base.ApiResponseContext
import com.universall.appcore.network.impl.api.base.middleware.ApiClientMiddleware
import com.universall.appcore.utils.logInfo
import com.universall.appcore.utils.logWarn
import com.universall.auth_api.domain.entities.AuthTokenPair
import com.universall.auth_api.domain.usecases.RefreshUseCase
import io.ktor.client.request.HttpRequestBuilder

class AutoRefreshTokensMiddleware(
    private val refreshUseCase: RefreshUseCase
) : ApiClientMiddleware<HttpRequestBuilder, ApiResponseContext<*>> {
    private suspend fun tryToRefreshTokens(): Result<AuthTokenPair> = refreshUseCase.invoke()

    override suspend fun proceed(
        context: ApiRequestContext,
        next: suspend (HttpRequestBuilder) -> Any?,
        input: HttpRequestBuilder
    ): ApiResponseContext<*> {
        return try {
            return next(input) as ApiResponseContext<*>
        } catch (error: InvalidAuthHttpException) {
            this.logInfo { "Failed to perform request ${input.method} - ${input.url} because of auth error: ${error::class.simpleName}: ${error.message ?: "null"}" }

            tryToRefreshTokens()
                .onFailure { error -> this.logWarn(error) { "Failed to refresh tokens, cancelling request ${input.method} - ${input.url}" } }
                .getOrThrow()

            this.logInfo { "Successfully refreshed tokens. Resending request ${input.method} - ${input.url}" }

            // New tokens will be automatically added later by AuthMiddleware
            val retryInput = HttpRequestBuilder().takeFrom(input)
            next(retryInput) as ApiResponseContext<*>
        }
    }
}
