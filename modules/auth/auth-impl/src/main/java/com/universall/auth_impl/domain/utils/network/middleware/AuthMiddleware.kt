package com.universall.auth_impl.domain.utils.network.middleware

import com.universall.appcore.network.api.base.ApiRequestContext
import com.universall.appcore.network.api.base.middleware.ApiClientMiddleware
import com.universall.auth_api.domain.usecases.GetTokenPairUseCase
import com.universall.core.exceptions.UnauthenticatedAppError
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.headers
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpHeaders

internal class AuthMiddleware(
    private val getTokenPairUseCase: GetTokenPairUseCase
) : ApiClientMiddleware<HttpRequestBuilder, HttpResponse> {
    private val authTokenType = "Bearer"

    private fun constructHeader(accessToken: String): String = "$authTokenType $accessToken"

    override suspend fun proceed(
        context: ApiRequestContext,
        next: suspend (HttpRequestBuilder) -> Any?,
        input: HttpRequestBuilder
    ): HttpResponse {
        if (context.getFromMeta<Boolean>("skipAuth") == true) return next(input) as HttpResponse

        val tokens = getTokenPairUseCase.invoke().getOrThrow() ?: throw UnauthenticatedAppError("App does not have access token")

        input.apply {
            headers {
                set(HttpHeaders.Authorization, constructHeader(tokens.accessToken))
            }
        }

        return next(input) as HttpResponse
    }
}
