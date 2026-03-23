package com.universall.appcore.ui.fields.generics

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.adamglin.PhosphorIcons
import com.adamglin.phosphoricons.Regular
import com.adamglin.phosphoricons.regular.Eye
import com.adamglin.phosphoricons.regular.EyeClosed
import com.universall.appcore.ui.fields.AppOutlinedField
import com.universall.appcore.ui.fields.FieldState
import com.universall.appcore.ui.onPressed
import com.universall.appcore.ui.text.AppTextStyle
import com.universall.appcore.ui.text.generics.AppTextDefaults

@Composable
private fun ShowPasswordIcon(
    isPasswordVisible: Boolean,
    onVisibilityChange: (Boolean) -> Unit
) {
    Icon(
        modifier = Modifier
            .size(16.dp)
            .onPressed {
                onVisibilityChange(true)

                try {
                    awaitRelease()
                } finally {
                    onVisibilityChange(false)
                }
            },
        imageVector = if (isPasswordVisible) PhosphorIcons.Regular.Eye else PhosphorIcons.Regular.EyeClosed,
        contentDescription = null
    )
}

@Composable
fun PasswordOutlinedField(
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
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Done, keyboardType= KeyboardType.Password, autoCorrectEnabled = false),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource? = null,
    shape: Shape = OutlinedTextFieldDefaults.shape,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
) {
    var isPasswordVisible by remember { mutableStateOf(false) }

    AppOutlinedField(
        modifier = modifier,
        onFocused = onFocused,
        onUnfocused = onUnfocused,
        state = state,
        onValueChange = onValueChange,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle,
        label = label,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = { ShowPasswordIcon(isPasswordVisible) { isPasswordVisible = it } },
        prefix = prefix,
        suffix = suffix,
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
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
