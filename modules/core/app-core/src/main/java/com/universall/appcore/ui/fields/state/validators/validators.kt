package com.universall.appcore.ui.fields.state.validators

import com.universall.appcore.ui.fields.state.FieldState
import com.universall.appcore.ui.fields.state.FieldState.Builder
import com.universall.appcore.utils.isNullOrEmpty

inline fun <T, S : FieldState<T>> S.validate(
    crossinline validator: Builder<T>.() -> Unit
): S {
    val builder = FieldState.builderFrom(this).apply(validator)
    builder.validated = true

    @Suppress("UNCHECKED_CAST", "KotlinConstantConditions")
    return this.withParts(
        value = builder.value,
        errorMessage = builder.errorMessage,
        enabled = builder.enabled,
        validated = builder.validated
    ) as S
}

fun <T, S : FieldState<T>> S.setNewValue(value: T): S {
    @Suppress("UNCHECKED_CAST")
    return withParts(
        value = value,
        errorMessage = null,
        validated = false
    ) as S
}

fun FieldState<*>.isOk(): Boolean =
    validated && errorMessage.isNullOrEmpty()
