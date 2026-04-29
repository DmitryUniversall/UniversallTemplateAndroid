package com.universall.appcore.appcore.network.exceptions

import com.universall.appcore.base.network.api.ApiRequest
import com.universall.appcore.base.network.api.ApiResponse
import com.universall.appcore.base.network.exceptions.ApiCallException


// GENERIC_ERRORS (2000)

open class InternalServerErrorHttpException(
    message: String? = null,
    request: ApiRequest,
    response: ApiResponse
) : ApiCallException(message = message, request = request, response = response)

open class AlreadyExistsHttpException(
    message: String? = null,
    request: ApiRequest,
    response: ApiResponse
) : ApiCallException(message = message, request = request, response = response)

open class NotFoundHttpException(
    message: String? = null,
    request: ApiRequest,
    response: ApiResponse
) : ApiCallException(message = message, request = request, response = response)

open class UnprocessableEntityHttpException(
    message: String? = null,
    request: ApiRequest,
    response: ApiResponse
) : ApiCallException(message = message, request = request, response = response)

open class TimeOutHttpException(
    message: String? = null,
    request: ApiRequest,
    response: ApiResponse
) : ApiCallException(message = message, request = request, response = response)

open class BadRequestHttpException(
    message: String? = null,
    request: ApiRequest,
    response: ApiResponse
) : ApiCallException(message = message, request = request, response = response)

open class ForbiddenHttpException(
    message: String? = null,
    request: ApiRequest,
    response: ApiResponse
) : ApiCallException(message = message, request = request, response = response)

open class OperationCancelledHttpException(
    message: String? = null,
    request: ApiRequest,
    response: ApiResponse
) : ApiCallException(message = message, request = request, response = response)

open class RouteDoesNotExistsHttpException(
    message: String? = null,
    request: ApiRequest,
    response: ApiResponse
) : ApiCallException(message = message, request = request, response = response)

open class NotImplementedHttpException(
    message: String? = null,
    request: ApiRequest,
    response: ApiResponse
) : ApiCallException(message = message, request = request, response = response)

open class MethodNotSupportedHttpException(
    message: String? = null,
    request: ApiRequest,
    response: ApiResponse
) : ApiCallException(message = message, request = request, response = response)

open class AlreadyInUseHttpException(
    message: String? = null,
    request: ApiRequest,
    response: ApiResponse
) : ApiCallException(message = message, request = request, response = response)

open class ExpiredHttpException(
    message: String? = null,
    request: ApiRequest,
    response: ApiResponse
) : ApiCallException(message = message, request = request, response = response)


// AUTH (3000)

open class InvalidAuthHttpException(
    message: String? = null,
    request: ApiRequest,
    response: ApiResponse
) : ApiCallException(message = message, request = request, response = response)

open class AuthorizationNotSpecifiedHttpException(
    message: String? = null,
    request: ApiRequest,
    response: ApiResponse
) : ApiCallException(message = message, request = request, response = response)

open class AuthorizationInvalidHttpException(
    message: String? = null,
    request: ApiRequest,
    response: ApiResponse
) : InvalidAuthHttpException(message = message, request = request, response = response)

open class AuthorizationTypeUnknownHttpException(
    message: String? = null,
    request: ApiRequest,
    response: ApiResponse
) : ApiCallException(message = message, request = request, response = response)

open class TokenNotSpecifiedHttpException(
    message: String? = null,
    request: ApiRequest,
    response: ApiResponse
) : ApiCallException(message = message, request = request, response = response)

open class TokenExpiredHttpException(
    message: String? = null,
    request: ApiRequest,
    response: ApiResponse
) : InvalidAuthHttpException(message = message, request = request, response = response)

open class TokenInvalidHttpException(
    message: String? = null,
    request: ApiRequest,
    response: ApiResponse
) : InvalidAuthHttpException(message = message, request = request, response = response)

open class TokenValidationFailedHttpException(
    message: String? = null,
    request: ApiRequest,
    response: ApiResponse
) : InvalidAuthHttpException(message = message, request = request, response = response)

open class UnknownUserHttpException(
    message: String? = null,
    request: ApiRequest,
    response: ApiResponse
) : InvalidAuthHttpException(message = message, request = request, response = response)

open class WrongAuthCredentialsHttpException(
    message: String? = null,
    request: ApiRequest,
    response: ApiResponse
) : ApiCallException(message = message, request = request, response = response)

open class InvalidSessionHttpException(
    message: String? = null,
    request: ApiRequest,
    response: ApiResponse
) : InvalidAuthHttpException(message = message, request = request, response = response)

open class InactiveSessionHttpException(
    message: String? = null,
    request: ApiRequest,
    response: ApiResponse
) : InvalidAuthHttpException(message = message, request = request, response = response)

open class SuspiciousActivityHttpException(
    message: String? = null,
    request: ApiRequest,
    response: ApiResponse
) : InvalidAuthHttpException(message = message, request = request, response = response)

open class AuthFailedUnknownErrorHttpException(
    message: String? = null,
    request: ApiRequest,
    response: ApiResponse
) : InvalidAuthHttpException(message = message, request = request, response = response)

open class AccessDeniedHttpException(
    message: String? = null,
    request: ApiRequest,
    response: ApiResponse
) : ApiCallException(message = message, request = request, response = response)

open class UnknownHttpException(
    message: String? = null,
    request: ApiRequest,
    response: ApiResponse
) : ApiCallException(message = message, request = request, response = response)
