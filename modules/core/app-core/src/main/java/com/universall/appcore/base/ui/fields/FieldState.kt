package com.universall.appcore.base.ui.fields

import com.universall.appcore.appcore.ui.fields.state.DefaultFieldState
import com.universall.appcore.appcore.ui.utils.UIString
import com.universall.core.collections.annotations.ConventionImmutable

@ConventionImmutable
interface FieldState<@ConventionImmutable T> {
    val value: T
    val errorMessage: UIString?
    val enabled: Boolean
    val validated: Boolean

    fun withParts(
        value: T = this.value,
        errorMessage: UIString? = this.errorMessage,
        enabled: Boolean = this.enabled,
        validated: Boolean = this.validated
    ): FieldState<T>

    class Builder<T>(
        var value: T,
        var errorMessage: UIString? = null,
        var enabled: Boolean = true,
        var validated: Boolean = false
    )

    companion object {
        fun <T> of(
            value: T,
            errorMessage: UIString? = null,
            enabled: Boolean = true,
            validated: Boolean = false
        ): FieldState<T> = DefaultFieldState(
            value = value,
            errorMessage = errorMessage,
            enabled = enabled,
            validated = validated
        )

        fun <T> builderFrom(state: FieldState<T>): Builder<T> = Builder(
            value = state.value,
            errorMessage = state.errorMessage,
            enabled = state.enabled,
            validated = state.validated
        )
    }
}
