package com.universall.appcore.base.network.api.attributes

class ApiContextAttributes(
    private val values: MutableMap<ApiCallAttributeKey<*>, Any> = mutableMapOf()
) {
    @Suppress("UNCHECKED_CAST")
    fun <T : Any> get(key: ApiCallAttributeKey<T>): T? {
        return values[key] as? T
    }

    fun <T : Any> put(key: ApiCallAttributeKey<T>, value: T) {
        values[key] = value
    }

    fun <T : Any> remove(key: ApiCallAttributeKey<T>) {
        values.remove(key)
    }

    fun copy(): ApiContextAttributes {
        return ApiContextAttributes(values.toMutableMap())
    }
}
