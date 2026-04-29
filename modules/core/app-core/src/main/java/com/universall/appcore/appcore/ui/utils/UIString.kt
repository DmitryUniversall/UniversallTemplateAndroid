package com.universall.appcore.appcore.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.res.stringResource
import com.universall.appcore.appcore.ui.utils.UIString.DynamicString
import com.universall.appcore.appcore.ui.utils.UIString.StringRes
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList

@Immutable
sealed interface UIString {
    @Immutable
    data class DynamicString(val value: String) : UIString

    @Immutable
    data class StringRes(@param:androidx.annotation.StringRes val resId: Int, val args: PersistentList<Arg> = persistentListOf()) : UIString

    @Immutable
    sealed interface Arg {
        @JvmInline
        value class Text(val value: String) : Arg

        @JvmInline
        value class IntArg(val value: Int) : Arg

        @JvmInline
        value class LongArg(val value: Long) : Arg

        @JvmInline
        value class FloatArg(val value: Float) : Arg

        @JvmInline
        value class DoubleArg(val value: Double) : Arg
    }

    companion object {
        fun of(value: String): UIString = DynamicString(value)
        fun resource(@androidx.annotation.StringRes resId: Int, vararg args: Arg): UIString = StringRes(resId, args.toList().toPersistentList())

        fun empty(): UIString = of("")
    }

    fun isEmpty(): Boolean = this is DynamicString && this.value.isEmpty()
}

private fun UIString.Arg.unwrap(): Any =
    when (this) {
        is UIString.Arg.Text -> value
        is UIString.Arg.IntArg -> value
        is UIString.Arg.LongArg -> value
        is UIString.Arg.FloatArg -> value
        is UIString.Arg.DoubleArg -> value
    }

fun UIString?.isNullOrEmpty(): Boolean = this == null || this.isEmpty()

@Composable
fun UIString.asString(): String = when (this) {
    is DynamicString -> value
    is StringRes -> stringResource(resId, *args.map { it.unwrap() }.toTypedArray())
}

@Composable
inline fun UIString.asStringOrIfEmpty(defaultValue: () -> String): String = if (isEmpty()) defaultValue() else this.asString()
