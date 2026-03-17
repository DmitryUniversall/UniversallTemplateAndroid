package com.universall.appcore.policies.impl.app_code

import com.universall.core.network.exceptions.*
import com.universall.core.policies.app_code.AppCodeProcessingPolicy
import io.ktor.http.HttpStatusCode

class AppCodeProcessingPolicyImpl : AppCodeProcessingPolicy {
    override fun process(httpStatusCode: HttpStatusCode, appCode: Int, message: String) {
        if (appCode in 1000..1999) return

        throw when (appCode) {
            // Generic errors (2000)
            2000 -> InternalServerErrorHttpException(message, appCode, httpStatusCode)
            2001 -> AlreadyExistsHttpException(message, appCode, httpStatusCode)
            2002 -> NotFoundHttpException(message, appCode, httpStatusCode)
            2003 -> UnprocessableEntityHttpException(message, appCode, httpStatusCode)
            2004 -> TimeOutHttpException(message, appCode, httpStatusCode)
            2005 -> BadRequestHttpException(message, appCode, httpStatusCode)
            2006 -> ForbiddenHttpException(message, appCode, httpStatusCode)
            2007 -> OperationCancelledHttpException(message, appCode, httpStatusCode)
            2008 -> RouteDoesNotExistsHttpException(message, appCode, httpStatusCode)
            2009 -> NotImplementedHttpException(message, appCode, httpStatusCode)
            2010 -> MethodNotSupportedHttpException(message, appCode, httpStatusCode)
            2011 -> AlreadyInUseHttpException(message, appCode, httpStatusCode)
            2012 -> ExpiredHttpException(message, appCode, httpStatusCode)

            // Auth (3000)
            3001 -> AuthorizationNotSpecifiedHttpException(message, appCode, httpStatusCode)
            3002 -> AuthorizationInvalidHttpException(message, appCode, httpStatusCode)
            3003 -> AuthorizationTypeUnknownHttpException(message, appCode, httpStatusCode)
            3004 -> TokenNotSpecifiedHttpException(message, appCode, httpStatusCode)
            3005 -> TokenExpiredHttpException(message, appCode, httpStatusCode)
            3006 -> TokenInvalidHttpException(message, appCode, httpStatusCode)
            3007 -> TokenValidationFailedHttpException(message, appCode, httpStatusCode)
            3008 -> UnknownUserHttpException(message, appCode, httpStatusCode)
            3009 -> WrongAuthCredentialsHttpException(message, appCode, httpStatusCode)
            3010 -> InvalidSessionHttpException(message, appCode, httpStatusCode)
            3011 -> InactiveSessionHttpException(message, appCode, httpStatusCode)
            3012 -> SuspiciousActivityHttpException(message, appCode, httpStatusCode)
            3013 -> AuthFailedUnknownErrorHttpException(message, appCode, httpStatusCode)
            3014 -> AccessDeniedHttpException(message, appCode, httpStatusCode)

            else -> UnknownHttpException(message, appCode, httpStatusCode)
        }
    }
}
