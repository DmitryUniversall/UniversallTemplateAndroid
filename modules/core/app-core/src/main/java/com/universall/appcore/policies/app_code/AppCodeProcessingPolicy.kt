package com.universall.appcore.policies.app_code

import io.ktor.http.HttpStatusCode

interface AppCodeProcessingPolicy {
    fun process(httpStatusCode: HttpStatusCode, appCode: Int, message: String)
}
