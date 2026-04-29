package com.universall.appcore.base.network.exceptions

import com.universall.appcore.base.network.api.ApiRequest
import com.universall.appcore.base.network.api.ApiResponse
import com.universall.core.exceptions.ApplicationException

open class ApiCallException(
    message: String?,
    val request: ApiRequest,
    val response: ApiResponse
) : ApplicationException(message ?: "ApiCallException: ${response.httpResponse.status}: ${response.httpResponse.status.description})")
