package com.universall.appcore.ui.fields.fields

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.adamglin.PhosphorIcons
import com.adamglin.phosphoricons.Regular
import com.adamglin.phosphoricons.regular.Check
import com.universall.appcore.ui.fields.state.FieldState
import com.universall.appcore.ui.fields.state.validators.isOk
import com.universall.appcore.ui.text.AppText
import com.universall.appcore.ui.text.AppTextStyle
import com.universall.appcore.ui.text.generics.AppTextDefaults
import com.universall.appcore.ui.text.generics.components.TextBodySmall
import com.universall.appcore.ui.theme.locals.Locals
import com.universall.appcore.utils.asString
import com.universall.appcore.utils.isNullOrEmpty
import com.universall.core.collections.annotations.ConventionImmutable

@Composable
private fun FieldSuccessIcon() {
    val extraColors = Locals.extraColors

    Icon(
        modifier = Modifier.size(16.dp),
        imageVector = PhosphorIcons.Regular.Check,
        contentDescription = null,
        tint = extraColors.success
    )
}

@Composable
fun <@ConventionImmutable T> AppOutlinedField(
    // TODO: Make more universal, create overloads
    modifier: Modifier = Modifier,
    state: FieldState<T>,
    onValueChange: (String) -> Unit,
    onFocused: (() -> Unit)? = null,
    onUnfocused: (() -> Unit)? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: AppTextStyle = AppTextDefaults.bodyMediumStyle(),
    label: String? = null,
    placeholder: String? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource? = null,
    shape: Shape = OutlinedTextFieldDefaults.shape,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
) {
    var wasFocused by remember { mutableStateOf(false) }
    var hasBeenFocused by remember { mutableStateOf(false) }

    OutlinedTextField(
        modifier = modifier.onFocusChanged { focusState ->
            if (focusState.isFocused) hasBeenFocused = true
            if (wasFocused && !focusState.isFocused && hasBeenFocused) onUnfocused?.invoke()
            if (!wasFocused && focusState.isFocused) onFocused?.invoke()

            wasFocused = focusState.isFocused
        },
        value = state.value.toString(),
        onValueChange = onValueChange,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle.textStyle,
        label = label?.let { @Composable { AppText(text = it, style = textStyle) } },
        placeholder = placeholder?.let { @Composable { AppText(text = it, style = textStyle) } },
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        prefix = prefix,
        suffix = suffix ?: if (state.isOk()) (@Composable { FieldSuccessIcon() }) else null,
        supportingText = if (state.errorMessage.isNullOrEmpty()) null else (@Composable {
            TextBodySmall(
                text = state.errorMessage!!.asString(),
                color = colors.errorSupportingTextColor
            )
        }),
        isError = !state.errorMessage.isNullOrEmpty(),
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        interactionSource = interactionSource,
        shape = shape,
        colors = colors
    )
}
