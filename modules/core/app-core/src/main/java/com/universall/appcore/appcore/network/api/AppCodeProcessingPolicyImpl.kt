package com.universall.appcore.appcore.network.api

import com.universall.appcore.appcore.network.exceptions.AccessDeniedHttpException
import com.universall.appcore.appcore.network.exceptions.AlreadyExistsHttpException
import com.universall.appcore.appcore.network.exceptions.AlreadyInUseHttpException
import com.universall.appcore.appcore.network.exceptions.AuthFailedUnknownErrorHttpException
import com.universall.appcore.appcore.network.exceptions.AuthorizationInvalidHttpException
import com.universall.appcore.appcore.network.exceptions.AuthorizationNotSpecifiedHttpException
import com.universall.appcore.appcore.network.exceptions.AuthorizationTypeUnknownHttpException
import com.universall.appcore.appcore.network.exceptions.BadRequestHttpException
import com.universall.appcore.appcore.network.exceptions.ExpiredHttpException
import com.universall.appcore.appcore.network.exceptions.ForbiddenHttpException
import com.universall.appcore.appcore.network.exceptions.InactiveSessionHttpException
import com.universall.appcore.appcore.network.exceptions.InternalServerErrorHttpException
import com.universall.appcore.appcore.network.exceptions.InvalidSessionHttpException
import com.universall.appcore.appcore.network.exceptions.MethodNotSupportedHttpException
import com.universall.appcore.appcore.network.exceptions.NotFoundHttpException
import com.universall.appcore.appcore.network.exceptions.NotImplementedHttpException
import com.universall.appcore.appcore.network.exceptions.OperationCancelledHttpException
import com.universall.appcore.appcore.network.exceptions.RouteDoesNotExistsHttpException
import com.universall.appcore.appcore.network.exceptions.SuspiciousActivityHttpException
import com.universall.appcore.appcore.network.exceptions.TimeOutHttpException
import com.universall.appcore.appcore.network.exceptions.TokenExpiredHttpException
import com.universall.appcore.appcore.network.exceptions.TokenInvalidHttpException
import com.universall.appcore.appcore.network.exceptions.TokenNotSpecifiedHttpException
import com.universall.appcore.appcore.network.exceptions.TokenValidationFailedHttpException
import com.universall.appcore.appcore.network.exceptions.UnknownHttpException
import com.universall.appcore.appcore.network.exceptions.UnknownUserHttpException
import com.universall.appcore.appcore.network.exceptions.UnprocessableEntityHttpException
import com.universall.appcore.appcore.network.exceptions.WrongAuthCredentialsHttpException
import com.universall.appcore.base.network.api.ApiRequest
import com.universall.appcore.base.network.api.ApiResponse
import com.universall.appcore.base.network.api.AppCodeProcessingPolicy

internal object AppCodeProcessingPolicyImpl : AppCodeProcessingPolicy {
    private val successCodes = 1000..1999

    override fun process(appStatusCode: Int, request: ApiRequest, response: ApiResponse) {
        if (appStatusCode in successCodes) return
        throw resolve(appStatusCode, request, response)
    }

    private fun resolve(code: Int, request: ApiRequest, response: ApiResponse) =
        when (code) {
            // GENERIC ERRORS (2000)
            2000 -> InternalServerErrorHttpException(request = request, response = response)
            2001 -> AlreadyExistsHttpException(request = request, response = response)
            2002 -> NotFoundHttpException(request = request, response = response)
            2003 -> UnprocessableEntityHttpException(request = request, response = response)
            2004 -> TimeOutHttpException(request = request, response = response)
            2005 -> BadRequestHttpException(request = request, response = response)
            2006 -> ForbiddenHttpException(request = request, response = response)
            2007 -> OperationCancelledHttpException(request = request, response = response)
            2008 -> RouteDoesNotExistsHttpException(request = request, response = response)
            2009 -> NotImplementedHttpException(request = request, response = response)
            2010 -> MethodNotSupportedHttpException(request = request, response = response)
            2011 -> AlreadyInUseHttpException(request = request, response = response)
            2012 -> ExpiredHttpException(request = request, response = response)

            // AUTH (3000)
            3001 -> AuthorizationNotSpecifiedHttpException(request = request, response = response)
            3002 -> AuthorizationInvalidHttpException(request = request, response = response)
            3003 -> AuthorizationTypeUnknownHttpException(request = request, response = response)
            3004 -> TokenNotSpecifiedHttpException(request = request, response = response)
            3005 -> TokenExpiredHttpException(request = request, response = response)
            3006 -> TokenInvalidHttpException(request = request, response = response)
            3007 -> TokenValidationFailedHttpException(request = request, response = response)
            3008 -> UnknownUserHttpException(request = request, response = response)
            3009 -> WrongAuthCredentialsHttpException(request = request, response = response)
            3010 -> InvalidSessionHttpException(request = request, response = response)
            3011 -> InactiveSessionHttpException(request = request, response = response)
            3012 -> SuspiciousActivityHttpException(request = request, response = response)
            3013 -> AuthFailedUnknownErrorHttpException(request = request, response = response)
            3014 -> AccessDeniedHttpException(request = request, response = response)

            // Unknown
            else -> UnknownHttpException(
                message = "Unhandled application status code: $code",
                request = request,
                response = response
            )
        }
}
