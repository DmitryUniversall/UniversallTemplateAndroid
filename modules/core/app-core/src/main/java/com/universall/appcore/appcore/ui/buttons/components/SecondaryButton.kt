package com.universall.appcore.appcore.ui.buttons.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.universall.appcore.appcore.ui.buttons.AppButtonDefaults
import com.universall.appcore.base.ui.buttons.AppButtonStyle
import com.universall.appcore.base.ui.text.AppTextStyle
import com.universall.appcore.appcore.ui.text.AppTextDefaults

@Composable
fun SecondaryButton(
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
        buttonStyle = AppButtonDefaults.secondary(),
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
