package com.universall.core.network.api.middleware

import com.universall.core.network.api.ApiRequestContext

interface ApiClientMiddleware<In, Out> {
    suspend fun proceed(
        context: ApiRequestContext,
        next: suspend (In) -> Any?,
        input: In
    ): Out
}
