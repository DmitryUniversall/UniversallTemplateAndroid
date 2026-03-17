package com.universall.core.network.api

class ApiRequestContext private constructor(
    private val metadata: Map<String, Any> = HashMap()
) {
    @Suppress("UNCHECKED_CAST")
    fun <T> getFromMeta(key: String): T? {
        return metadata[key] as T?
    }

    class Builder(
        private val metadata: MutableMap<String, Any> = HashMap()
    ) {
        fun hasMeta(key: String): Boolean = metadata.contains(key)

        fun <T : Any> meta(key: String, value: T): Builder {
            metadata[key] = value
            return this
        }

        fun <T : Any> setDefaultMeta(key: String, value: T): Builder {
            if (!hasMeta(key)) meta(key, value)
            return this
        }

        fun build(): ApiRequestContext {
            return ApiRequestContext(metadata = metadata)
        }
    }
}
