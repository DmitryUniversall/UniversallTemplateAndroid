package com.universall.appcore.appcore.ui.fields.state

import androidx.compose.runtime.Immutable
import com.universall.appcore.base.ui.fields.FieldState
import com.universall.appcore.appcore.ui.utils.UIString

@Immutable
data class TextFieldState(
    override val value: String,
    override val errorMessage: UIString? = null,
    override val enabled: Boolean = true,
    override val validated: Boolean = false
) : FieldState<String> {
    override fun withParts(
        value: String,
        errorMessage: UIString?,
        enabled: Boolean,
        validated: Boolean
    ): TextFieldState = copy(
        value = value,
        errorMessage = errorMessage,
        enabled = enabled,
        validated = validated
    )
}
