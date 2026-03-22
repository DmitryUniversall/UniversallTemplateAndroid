package com.universall.appcore.ui.fields

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.VisualTransformation
import com.universall.appcore.ui.text.AppText
import com.universall.appcore.ui.text.AppTextStyle
import com.universall.appcore.ui.text.generics.AppTextDefaults
import com.universall.appcore.ui.text.generics.components.TextBodySmall
import com.universall.appcore.utils.isNullOrEmpty

@Composable
fun AppOutlinedField(
    // TODO: Make more universal, create overloads
    modifier: Modifier = Modifier,
    state: FieldState<String>,
    onValueChange: (String) -> Unit,
    onFocused: (() -> Unit)? = null,
    onUnfocused: (() -> Unit)? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: AppTextStyle = AppTextDefaults.bodyMediumStyle(),
    label: String? = null,
    placeholder: String? = null,
    prefix: String? = null,
    suffix: String? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource? = null,
    shape: Shape = OutlinedTextFieldDefaults.shape,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
) {
    FocusObservableOutlinedField(
        modifier = modifier,
        onFocused = onFocused,
        onUnfocused = onUnfocused,
        value = state.value,
        onValueChange = onValueChange,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle.textStyle,
        label = label?.let { @Composable { AppText(text = it, style = textStyle) } },
        placeholder = placeholder?.let { @Composable { AppText(text = it, style = textStyle) } },
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        prefix = prefix?.let { @Composable { AppText(text = it) } },
        suffix = suffix?.let { @Composable { AppText(text = it) } },
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
