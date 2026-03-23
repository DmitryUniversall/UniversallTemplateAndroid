package com.universall.appcore.ui.fields

import com.universall.appcore.utils.UIString
import com.universall.appcore.utils.isNullOrEmpty

// TODO: Make it stable and immutable
data class FieldState<T>(
    val value: T,
    val errorMessage: UIString? = null,
    val enabled: Boolean = true,
    val validated: Boolean = false
) {
    class Builder<T>(
        var value: T,
        var errorMessage: UIString? = null,
        var enabled: Boolean = true,
        var validated: Boolean = false
    ) {
        companion object {
            fun <T> from(state: FieldState<T>): Builder<T> = Builder(
                value = state.value,
                errorMessage = state.errorMessage,
                enabled = state.enabled
            )
        }

        fun build(): FieldState<T> = FieldState(
            value = value,
            errorMessage = errorMessage,
            enabled = enabled,
            validated = validated
        )
    }

    inline fun validate(
        crossinline validator: Builder<T>.() -> Unit
    ): FieldState<T> = Builder.from(this).apply(validator).apply { validated = true }.build()

    fun isOk(): Boolean =
        this.validated && this.errorMessage.isNullOrEmpty()

    fun setNewValue(value: T) = this.copy(value = value, validated = false)
}
