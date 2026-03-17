package com.universall.core.network.exceptions

import io.ktor.http.HttpStatusCode


// GENERICS (Success) (1000)

open class SuccessHttpException(
    message: String? = "Success",
    appCode: Int = 1000,
    httpStatus: HttpStatusCode = HttpStatusCode.OK
) : ApplicationHttpException(httpStatus = httpStatus, appCode = appCode, message = message)

open class CreatedHttpException(
    message: String? = "Created",
    appCode: Int = 1001,
    httpStatus: HttpStatusCode = HttpStatusCode.Created
) : ApplicationHttpException(httpStatus = httpStatus, appCode = appCode, message = message)

open class UpdatedHttpException(
    message: String? = "Updated",
    appCode: Int = 1002,
    httpStatus: HttpStatusCode = HttpStatusCode.OK
) : ApplicationHttpException(httpStatus = httpStatus, appCode = appCode, message = message)

open class DeletedHttpException(
    message: String? = "Deleted",
    appCode: Int = 1003,
    httpStatus: HttpStatusCode = HttpStatusCode.NoContent
) : ApplicationHttpException(httpStatus = httpStatus, appCode = appCode, message = message)

open class NotChangedHttpException(
    message: String? = "Not changed",
    appCode: Int = 1004,
    httpStatus: HttpStatusCode = HttpStatusCode.NotModified
) : ApplicationHttpException(httpStatus = httpStatus, appCode = appCode, message = message)


// GENERIC_ERRORS (2000)

open class InternalServerErrorHttpException(
    message: String? = "Internal server error",
    appCode: Int = 2000,
    httpStatus: HttpStatusCode = HttpStatusCode.InternalServerError
) : ApplicationHttpException(httpStatus = httpStatus, appCode = appCode, message = message)

open class AlreadyExistsHttpException(
    message: String? = "Already exists",
    appCode: Int = 2001,
    httpStatus: HttpStatusCode = HttpStatusCode.Conflict
) : ApplicationHttpException(httpStatus = httpStatus, appCode = appCode, message = message)

open class NotFoundHttpException(
    message: String? = "Not found",
    appCode: Int = 2002,
    httpStatus: HttpStatusCode = HttpStatusCode.NotFound
) : ApplicationHttpException(httpStatus = httpStatus, appCode = appCode, message = message)

open class UnprocessableEntityHttpException(
    message: String? = "Unprocessable entity",
    appCode: Int = 2003,
    httpStatus: HttpStatusCode = HttpStatusCode.UnprocessableEntity
) : ApplicationHttpException(httpStatus = httpStatus, appCode = appCode, message = message)

open class TimeOutHttpException(
    message: String? = "Time out",
    appCode: Int = 2004,
    httpStatus: HttpStatusCode = HttpStatusCode.RequestTimeout
) : ApplicationHttpException(httpStatus = httpStatus, appCode = appCode, message = message)

open class BadRequestHttpException(
    message: String? = "Bad request",
    appCode: Int = 2005,
    httpStatus: HttpStatusCode = HttpStatusCode.BadRequest
) : ApplicationHttpException(httpStatus = httpStatus, appCode = appCode, message = message)

open class ForbiddenHttpException(
    message: String? = "Forbidden",
    appCode: Int = 2006,
    httpStatus: HttpStatusCode = HttpStatusCode.Forbidden
) : ApplicationHttpException(httpStatus = httpStatus, appCode = appCode, message = message)

open class OperationCancelledHttpException(
    message: String? = "Operation cancelled",
    appCode: Int = 2007,
    httpStatus: HttpStatusCode = HttpStatusCode.BadRequest
) : ApplicationHttpException(httpStatus = httpStatus, appCode = appCode, message = message)

open class RouteDoesNotExistsHttpException(
    message: String? = "Route does not exists",
    appCode: Int = 2008,
    httpStatus: HttpStatusCode = HttpStatusCode.NotFound
) : ApplicationHttpException(httpStatus = httpStatus, appCode = appCode, message = message)

open class NotImplementedHttpException(
    message: String? = "Not implemented yet",
    appCode: Int = 2009,
    httpStatus: HttpStatusCode = HttpStatusCode.NotImplemented
) : ApplicationHttpException(httpStatus = httpStatus, appCode = appCode, message = message)

open class MethodNotSupportedHttpException(
    message: String? = "Method not supported",
    appCode: Int = 2010,
    httpStatus: HttpStatusCode = HttpStatusCode.MethodNotAllowed
) : ApplicationHttpException(httpStatus = httpStatus, appCode = appCode, message = message)

open class AlreadyInUseHttpException(
    message: String? = "Resource is already in use",
    appCode: Int = 2011,
    httpStatus: HttpStatusCode = HttpStatusCode.Conflict
) : ApplicationHttpException(httpStatus = httpStatus, appCode = appCode, message = message)

open class ExpiredHttpException(
    message: String? = "Resource expired",
    appCode: Int = 2012,
    httpStatus: HttpStatusCode = HttpStatusCode.Gone
) : ApplicationHttpException(httpStatus = httpStatus, appCode = appCode, message = message)


// AUTH (3000)

open class InvalidAuthHttpException(
    message: String?,
    appCode: Int,
    httpStatus: HttpStatusCode = HttpStatusCode.Forbidden
) : ApplicationHttpException(httpStatus = httpStatus, appCode = appCode, message = message)

open class AuthorizationNotSpecifiedHttpException(
    message: String? = "Authorization was not specified",
    appCode: Int = 3001,
    httpStatus: HttpStatusCode = HttpStatusCode.Unauthorized
) : ApplicationHttpException(httpStatus = httpStatus, appCode = appCode, message = message)

open class AuthorizationInvalidHttpException(
    message: String? = "Invalid authorization",
    appCode: Int = 3002,
    httpStatus: HttpStatusCode = HttpStatusCode.Unauthorized
) : InvalidAuthHttpException(httpStatus = httpStatus, appCode = appCode, message = message)

open class AuthorizationTypeUnknownHttpException(
    message: String? = "Unknown authorization type",
    appCode: Int = 3003,
    httpStatus: HttpStatusCode = HttpStatusCode.BadRequest
) : ApplicationHttpException(httpStatus = httpStatus, appCode = appCode, message = message)

open class TokenNotSpecifiedHttpException(
    message: String? = "Authorization token was not specified",
    appCode: Int = 3004,
    httpStatus: HttpStatusCode = HttpStatusCode.Unauthorized
) : ApplicationHttpException(httpStatus = httpStatus, appCode = appCode, message = message)

open class TokenExpiredHttpException(
    message: String? = "Authorization token is expired",
    appCode: Int = 3005,
    httpStatus: HttpStatusCode = HttpStatusCode.Unauthorized
) : InvalidAuthHttpException(httpStatus = httpStatus, appCode = appCode, message = message)

open class TokenInvalidHttpException(
    message: String? = "Authorization token is invalid",
    appCode: Int = 3006,
    httpStatus: HttpStatusCode = HttpStatusCode.Unauthorized
) : InvalidAuthHttpException(httpStatus = httpStatus, appCode = appCode, message = message)

open class TokenValidationFailedHttpException(
    message: String? = "Authorization token validation failed",
    appCode: Int = 3007,
    httpStatus: HttpStatusCode = HttpStatusCode.Unauthorized
) : InvalidAuthHttpException(httpStatus = httpStatus, appCode = appCode, message = message)

open class UnknownUserHttpException(
    message: String? = "Invalid authorization data: User not found",
    appCode: Int = 3008,
    httpStatus: HttpStatusCode = HttpStatusCode.Unauthorized
) : InvalidAuthHttpException(httpStatus = httpStatus, appCode = appCode, message = message)

open class WrongAuthCredentialsHttpException(
    message: String? = "Wrong authentication credentials",
    appCode: Int = 3009,
    httpStatus: HttpStatusCode = HttpStatusCode.Unauthorized
) : ApplicationHttpException(httpStatus = httpStatus, appCode = appCode, message = message)

open class InvalidSessionHttpException(
    message: String? = "Invalid session specified",
    appCode: Int = 3010,
    httpStatus: HttpStatusCode = HttpStatusCode.Unauthorized
) : InvalidAuthHttpException(httpStatus = httpStatus, appCode = appCode, message = message)

open class InactiveSessionHttpException(
    message: String? = "This session is no more valid",
    appCode: Int = 3011,
    httpStatus: HttpStatusCode = HttpStatusCode.Unauthorized
) : InvalidAuthHttpException(httpStatus = httpStatus, appCode = appCode, message = message)

open class SuspiciousActivityHttpException(
    message: String? = "Suspicious activity detected, this session is no more valid",
    appCode: Int = 3012,
    httpStatus: HttpStatusCode = HttpStatusCode.Forbidden
) : InvalidAuthHttpException(httpStatus = httpStatus, appCode = appCode, message = message)

open class AuthFailedUnknownErrorHttpException(
    message: String? = "Unknown error occurred during auth process, request rejected",
    appCode: Int = 3013,
    httpStatus: HttpStatusCode = HttpStatusCode.Unauthorized
) : InvalidAuthHttpException(httpStatus = httpStatus, appCode = appCode, message = message)

open class AccessDeniedHttpException(
    message: String? = "Access denied",
    appCode: Int = 3014,
    httpStatus: HttpStatusCode = HttpStatusCode.Forbidden
) : ApplicationHttpException(httpStatus = httpStatus, appCode = appCode, message = message)

open class UnknownHttpException(
    message: String? = "Unknown http exception",
    appCode: Int = -1,
    httpStatus: HttpStatusCode
) : ApplicationHttpException(httpStatus = httpStatus, appCode = appCode, message = message)
