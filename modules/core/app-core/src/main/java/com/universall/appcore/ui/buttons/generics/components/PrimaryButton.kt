package com.universall.appcore.ui.buttons.generics.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.universall.appcore.ui.buttons.AppButtonStyle
import com.universall.appcore.ui.buttons.generics.AppButtonDefaults
import com.universall.appcore.ui.text.AppTextStyle
import com.universall.appcore.ui.text.generics.AppTextDefaults

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
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
        buttonStyle = AppButtonDefaults.primary(),
        text = text,
        enabled = enabled,
        leading = leading,
        trailing = trailing,
        buttonStyleOverrides = buttonStyleOverrides,
        textStyle = textStyle,
        textStyleOverrides = textStyleOverrides,
        content = content
    )
}
