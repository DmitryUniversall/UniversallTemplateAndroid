package com.universall.appcore.network.api.middleware

import com.universall.appcore.network.exceptions.AccessDeniedHttpException
import com.universall.appcore.network.exceptions.AlreadyExistsHttpException
import com.universall.appcore.network.exceptions.AlreadyInUseHttpException
import com.universall.appcore.network.exceptions.AuthFailedUnknownErrorHttpException
import com.universall.appcore.network.exceptions.AuthorizationInvalidHttpException
import com.universall.appcore.network.exceptions.AuthorizationNotSpecifiedHttpException
import com.universall.appcore.network.exceptions.AuthorizationTypeUnknownHttpException
import com.universall.appcore.network.exceptions.BadRequestHttpException
import com.universall.appcore.network.exceptions.ExpiredHttpException
import com.universall.appcore.network.exceptions.ForbiddenHttpException
import com.universall.appcore.network.exceptions.InactiveSessionHttpException
import com.universall.appcore.network.exceptions.InternalServerErrorHttpException
import com.universall.appcore.network.exceptions.InvalidSessionHttpException
import com.universall.appcore.network.exceptions.MethodNotSupportedHttpException
import com.universall.appcore.network.exceptions.NotFoundHttpException
import com.universall.appcore.network.exceptions.NotImplementedHttpException
import com.universall.appcore.network.exceptions.OperationCancelledHttpException
import com.universall.appcore.network.exceptions.RouteDoesNotExistsHttpException
import com.universall.appcore.network.exceptions.SuspiciousActivityHttpException
import com.universall.appcore.network.exceptions.TimeOutHttpException
import com.universall.appcore.network.exceptions.TokenExpiredHttpException
import com.universall.appcore.network.exceptions.TokenInvalidHttpException
import com.universall.appcore.network.exceptions.TokenNotSpecifiedHttpException
import com.universall.appcore.network.exceptions.TokenValidationFailedHttpException
import com.universall.appcore.network.exceptions.UnknownHttpException
import com.universall.appcore.network.exceptions.UnknownUserHttpException
import com.universall.appcore.network.exceptions.UnprocessableEntityHttpException
import com.universall.appcore.network.exceptions.WrongAuthCredentialsHttpException
import com.universall.appcore.network.api.base.ApiRequestContext
import com.universall.appcore.network.api.base.ApiResponseContext
import com.universall.appcore.network.api.base.middleware.ApiClientMiddleware
import io.ktor.client.request.HttpRequestBuilder

class AppCodeProcessingMiddleware : ApiClientMiddleware<HttpRequestBuilder, ApiResponseContext<*>> {
    override suspend fun proceed(
        context: ApiRequestContext,
        next: suspend (HttpRequestBuilder) -> Any?,
        input: HttpRequestBuilder
    ): ApiResponseContext<*> {
        val response = next(input) as ApiResponseContext<*>
        if (response.apiResponse.appCode in 1000..1999) return response

        throw when (response.apiResponse.appCode) {
            // Generic errors (2000)
            2000 -> InternalServerErrorHttpException(responseContext = response)
            2001 -> AlreadyExistsHttpException(responseContext = response)
            2002 -> NotFoundHttpException(responseContext = response)
            2003 -> UnprocessableEntityHttpException(responseContext = response)
            2004 -> TimeOutHttpException(responseContext = response)
            2005 -> BadRequestHttpException(responseContext = response)
            2006 -> ForbiddenHttpException(responseContext = response)
            2007 -> OperationCancelledHttpException(responseContext = response)
            2008 -> RouteDoesNotExistsHttpException(responseContext = response)
            2009 -> NotImplementedHttpException(responseContext = response)
            2010 -> MethodNotSupportedHttpException(responseContext = response)
            2011 -> AlreadyInUseHttpException(responseContext = response)
            2012 -> ExpiredHttpException(responseContext = response)

            // Auth (3000)
            3001 -> AuthorizationNotSpecifiedHttpException(responseContext = response)
            3002 -> AuthorizationInvalidHttpException(responseContext = response)
            3003 -> AuthorizationTypeUnknownHttpException(responseContext = response)
            3004 -> TokenNotSpecifiedHttpException(responseContext = response)
            3005 -> TokenExpiredHttpException(responseContext = response)
            3006 -> TokenInvalidHttpException(responseContext = response)
            3007 -> TokenValidationFailedHttpException(responseContext = response)
            3008 -> UnknownUserHttpException(responseContext = response)
            3009 -> WrongAuthCredentialsHttpException(responseContext = response)
            3010 -> InvalidSessionHttpException(responseContext = response)
            3011 -> InactiveSessionHttpException(responseContext = response)
            3012 -> SuspiciousActivityHttpException(responseContext = response)
            3013 -> AuthFailedUnknownErrorHttpException(responseContext = response)
            3014 -> AccessDeniedHttpException(responseContext = response)

            else -> UnknownHttpException(responseContext = response)
        }
    }
}
