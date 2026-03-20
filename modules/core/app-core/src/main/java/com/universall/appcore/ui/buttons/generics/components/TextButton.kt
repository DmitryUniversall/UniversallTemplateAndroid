package com.universall.appcore.ui.buttons.generics.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import com.universall.appcore.ui.buttons.AppButtonStyle
import com.universall.appcore.ui.buttons.generics.AppButtonDefaults
import com.universall.appcore.ui.text.AppTextStyle
import com.universall.appcore.ui.text.generics.AppTextDefaults
import com.universall.appcore.ui.text.override

@Composable
fun TextButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    buttonStyle: AppButtonStyle = AppButtonDefaults.text(),
    text: String? = null,
    enabled: Boolean = true,
    leading: (@Composable (() -> Unit))? = null,
    trailing: (@Composable (() -> Unit))? = null,
    buttonStyleOverrides: (AppButtonStyle.Builder.() -> Unit)? = null,
    textStyle: AppTextStyle = AppTextDefaults.headlineSmallStyle(),
    textStyleOverrides: (AppTextStyle.Builder.() -> Unit)? = null,
    content: (@Composable RowScope.() -> Unit)? = null
) {
    GenericStyledButton(
        modifier = modifier,
        onClick = onClick,
        buttonStyle = buttonStyle,
        text = text,
        enabled = enabled,
        leading = leading,
        trailing = trailing,
        buttonStyleOverrides = buttonStyleOverrides,
        textStyle = textStyle.override { textDecoration = TextDecoration.Underline },
        textStyleOverrides = textStyleOverrides,
        content = content
    )
}
