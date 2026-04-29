package com.universall.appcore.base.network.api

import com.universall.appcore.base.network.api.attributes.ApiContextAttributes
import io.ktor.client.statement.HttpResponse

data class ApiResponse(
    val httpResponse: HttpResponse,
    val attributes: ApiContextAttributes = ApiContextAttributes()
)
