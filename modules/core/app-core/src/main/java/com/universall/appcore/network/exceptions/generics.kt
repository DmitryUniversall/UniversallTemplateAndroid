package com.universall.appcore.network.exceptions

import io.ktor.http.HttpStatusCode


open class InternalServerErrorHttpException(
    message: String?,
    appCode: Int = 2000,
    httpStatus: HttpStatusCode
) : ApplicationHttpException(
    httpStatus = httpStatus,
    appCode = appCode,
    message = message
)

open class AlreadyExistsHttpException(
    message: String?,
    appCode: Int = 2001,
    httpStatus: HttpStatusCode
) : ApplicationHttpException(
    httpStatus = httpStatus,
    appCode = appCode,
    message = message
)

open class NotFoundHttpException(
    message: String?,
    appCode: Int = 2002,
    httpStatus: HttpStatusCode
) : ApplicationHttpException(
    httpStatus = httpStatus,
    appCode = appCode,
    message = message
)

open class BadRequestHttpException(
    message: String?,
    appCode: Int = 2005,
    httpStatus: HttpStatusCode
) : ApplicationHttpException(
    httpStatus = httpStatus,
    appCode = appCode,
    message = message
)

open class AuthExpiredHttpException(
    message: String?,
    appCode: Int,
    httpStatus: HttpStatusCode
) : ApplicationHttpException(
    httpStatus = httpStatus,
    appCode = appCode,
    message = message
)

open class UnknownHttpException(
    message: String?,
    appCode: Int = -1,
    httpStatus: HttpStatusCode
) : ApplicationHttpException(
    httpStatus = httpStatus,
    appCode = appCode,
    message = message
)
