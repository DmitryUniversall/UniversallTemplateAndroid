package com.universall.appcore.network.exceptions

import com.universall.appcore.network.api.base.ApiResponseContext
import com.universall.core.exceptions.ApplicationException

open class ApiCallException(
    message: String?,
    val responseContext: ApiResponseContext<*>
) : ApplicationException(message ?: "ApiCallException: ${responseContext.httpResponse.status} - (${responseContext.apiResponse.appCode}: ${responseContext.apiResponse.message})")
