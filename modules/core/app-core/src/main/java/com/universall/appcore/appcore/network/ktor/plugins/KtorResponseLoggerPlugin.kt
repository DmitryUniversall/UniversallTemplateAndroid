package com.universall.appcore.appcore.network.ktor.plugins

import android.util.Log
import io.ktor.client.plugins.api.createClientPlugin
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.request

object KtorResponseLoggerPlugin {
    val plugin = createClientPlugin("KtorResponseLoggerPlugin") {
        onResponse { response ->
            val body = response.bodyAsText()
            val url = response.request.url
            val method = response.request.method

            Log.d("KtorResponseLoggerPlugin", "RESPONSE $method $url; Body: $body")
        }
    }
}
