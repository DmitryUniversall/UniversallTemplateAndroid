package com.universall.appcore.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed interface UIString {
    data class DynamicString(val value: String) : UIString
    data class StringRes(@param:androidx.annotation.StringRes val resId: Int, val args: List<Any> = emptyList()) : UIString

    companion object {
        fun of(value: String): UIString = DynamicString(value)
        fun resource(@androidx.annotation.StringRes resId: Int, vararg args: Any): UIString = StringRes(resId, args.toList())

        fun empty(): UIString = of("")
    }

    @Composable
    fun asString(): String {
        return when (this) {
            is DynamicString -> value
            is StringRes -> stringResource(resId, *args.toTypedArray())
        }
    }

    fun isEmpty(): Boolean = this is DynamicString && this.value.isEmpty()
}

fun UIString?.isNullOrEmpty(): Boolean =
    this == null || this.isEmpty()

@Composable
inline fun UIString.asStringOrIfEmpty(defaultValue: () -> String): String =
    if (isEmpty()) defaultValue() else this.asString()
