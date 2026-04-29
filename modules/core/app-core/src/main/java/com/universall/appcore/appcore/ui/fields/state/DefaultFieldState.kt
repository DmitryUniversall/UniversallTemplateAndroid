package com.universall.appcore.appcore.ui.fields.state

import com.universall.appcore.base.ui.fields.FieldState
import com.universall.appcore.appcore.ui.utils.UIString

data class DefaultFieldState<T>(
    override val value: T,
    override val errorMessage: UIString? = null,
    override val enabled: Boolean = true,
    override val validated: Boolean = false
) : FieldState<T> {
    override fun withParts(
        value: T,
        errorMessage: UIString?,
        enabled: Boolean,
        validated: Boolean
    ): FieldState<T> = copy(
        value = value,
        errorMessage = errorMessage,
        enabled = enabled,
        validated = validated
    )
}
