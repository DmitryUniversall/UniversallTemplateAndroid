package com.universall.core.network.exceptions

import com.universall.core.exceptions.ApplicationException
import io.ktor.http.HttpStatusCode

open class ApplicationHttpException(
    val httpStatus: HttpStatusCode,
    val appCode: Int,
    message: String?
) : ApplicationException(message ?: "HttpException: $httpStatus - ${httpStatus.description} ($appCode)")
