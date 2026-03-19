package com.universall.appcore.network.exceptions

import com.universall.appcore.network.impl.api.base.ApiResponseContext


// GENERIC_ERRORS (2000)

open class InternalServerErrorHttpException(
    message: String? = null,
    responseContext: ApiResponseContext<*>
) : ApiCallException(message = message, responseContext = responseContext)

open class AlreadyExistsHttpException(
    message: String? = null,
    responseContext: ApiResponseContext<*>
) : ApiCallException(message = message, responseContext = responseContext)

open class NotFoundHttpException(
    message: String? = null,
    responseContext: ApiResponseContext<*>
) : ApiCallException(message = message, responseContext = responseContext)

open class UnprocessableEntityHttpException(
    message: String? = null,
    responseContext: ApiResponseContext<*>
) : ApiCallException(message = message, responseContext = responseContext)

open class TimeOutHttpException(
    message: String? = null,
    responseContext: ApiResponseContext<*>
) : ApiCallException(message = message, responseContext = responseContext)

open class BadRequestHttpException(
    message: String? = null,
    responseContext: ApiResponseContext<*>
) : ApiCallException(message = message, responseContext = responseContext)

open class ForbiddenHttpException(
    message: String? = null,
    responseContext: ApiResponseContext<*>
) : ApiCallException(message = message, responseContext = responseContext)

open class OperationCancelledHttpException(
    message: String? = null,
    responseContext: ApiResponseContext<*>
) : ApiCallException(message = message, responseContext = responseContext)

open class RouteDoesNotExistsHttpException(
    message: String? = null,
    responseContext: ApiResponseContext<*>
) : ApiCallException(message = message, responseContext = responseContext)

open class NotImplementedHttpException(
    message: String? = null,
    responseContext: ApiResponseContext<*>
) : ApiCallException(message = message, responseContext = responseContext)

open class MethodNotSupportedHttpException(
    message: String? = null,
    responseContext: ApiResponseContext<*>
) : ApiCallException(message = message, responseContext = responseContext)

open class AlreadyInUseHttpException(
    message: String? = null,
    responseContext: ApiResponseContext<*>
) : ApiCallException(message = message, responseContext = responseContext)

open class ExpiredHttpException(
    message: String? = null,
    responseContext: ApiResponseContext<*>
) : ApiCallException(message = message, responseContext = responseContext)


// AUTH (3000)

open class InvalidAuthHttpException(
    message: String?,
    responseContext: ApiResponseContext<*>
) : ApiCallException(message = message, responseContext = responseContext)

open class AuthorizationNotSpecifiedHttpException(
    message: String? = null,
    responseContext: ApiResponseContext<*>
) : ApiCallException(message = message, responseContext = responseContext)

open class AuthorizationInvalidHttpException(
    message: String? = null,
    responseContext: ApiResponseContext<*>
) : InvalidAuthHttpException(message = message, responseContext = responseContext)

open class AuthorizationTypeUnknownHttpException(
    message: String? = null,
    responseContext: ApiResponseContext<*>
) : ApiCallException(message = message, responseContext = responseContext)

open class TokenNotSpecifiedHttpException(
    message: String? = null,
    responseContext: ApiResponseContext<*>
) : ApiCallException(message = message, responseContext = responseContext)

open class TokenExpiredHttpException(
    message: String? = null,
    responseContext: ApiResponseContext<*>
) : InvalidAuthHttpException(message = message, responseContext = responseContext)

open class TokenInvalidHttpException(
    message: String? = null,
    responseContext: ApiResponseContext<*>
) : InvalidAuthHttpException(message = message, responseContext = responseContext)

open class TokenValidationFailedHttpException(
    message: String? = null,
    responseContext: ApiResponseContext<*>
) : InvalidAuthHttpException(message = message, responseContext = responseContext)

open class UnknownUserHttpException(
    message: String? = null,
    responseContext: ApiResponseContext<*>
) : InvalidAuthHttpException(message = message, responseContext = responseContext)

open class WrongAuthCredentialsHttpException(
    message: String? = null,
    responseContext: ApiResponseContext<*>
) : ApiCallException(message = message, responseContext = responseContext)

open class InvalidSessionHttpException(
    message: String? = null,
    responseContext: ApiResponseContext<*>
) : InvalidAuthHttpException(message = message, responseContext = responseContext)

open class InactiveSessionHttpException(
    message: String? = null,
    responseContext: ApiResponseContext<*>
) : InvalidAuthHttpException(message = message, responseContext = responseContext)

open class SuspiciousActivityHttpException(
    message: String? = null,
    responseContext: ApiResponseContext<*>
) : InvalidAuthHttpException(message = message, responseContext = responseContext)

open class AuthFailedUnknownErrorHttpException(
    message: String? = null,
    responseContext: ApiResponseContext<*>
) : InvalidAuthHttpException(message = message, responseContext = responseContext)

open class AccessDeniedHttpException(
    message: String? = null,
    responseContext: ApiResponseContext<*>
) : ApiCallException(message = message, responseContext = responseContext)

open class UnknownHttpException(
    message: String? = null,
    responseContext: ApiResponseContext<*>
) : ApiCallException(message = message, responseContext = responseContext)
