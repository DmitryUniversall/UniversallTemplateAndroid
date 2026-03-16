package com.universall.appcore.network.api.middleware

import com.universall.appcore.network.api.ApiRequestContext

interface ApiClientMiddleware<In, Out> {
    suspend fun proceed(
        context: ApiRequestContext,
        next: suspend (In) -> Any?,
        input: In
    ): Out
}
