package com.universall.appcore.network.api.base.middleware

import com.universall.appcore.network.api.base.ApiRequestContext

interface ApiClientMiddleware<In, Out> {
    suspend fun proceed(
        context: ApiRequestContext,
        next: suspend (In) -> Any?,
        input: In
    ): Out
}
