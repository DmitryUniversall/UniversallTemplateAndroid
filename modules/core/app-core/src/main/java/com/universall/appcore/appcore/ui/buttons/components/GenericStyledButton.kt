package com.universall.appcore.appcore.ui.buttons.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.universall.appcore.appcore.ui.buttons.AppButtonDefaults
import com.universall.appcore.base.ui.buttons.AppButton
import com.universall.appcore.base.ui.buttons.AppButtonStyle
import com.universall.appcore.base.ui.buttons.override
import com.universall.appcore.base.ui.text.AppTextStyle
import com.universall.appcore.appcore.ui.text.AppTextDefaults
import com.universall.appcore.base.ui.text.override

@Composable
internal fun GenericStyledButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    buttonStyle: AppButtonStyle,
    text: String? = null,
    enabled: Boolean = true,
    leading: (@Composable (() -> Unit))? = null,
    trailing: (@Composable (() -> Unit))? = null,
    buttonStyleOverrides: (AppButtonStyle.Builder.() -> Unit)? = null,
    textStyle: AppTextStyle = AppTextDefaults.headlineSmallStyle(),
    textStyleOverrides: (AppTextStyle.Builder.() -> Unit)? = null,
    content: (@Composable RowScope.() -> Unit)? = null
) {
    val finalButtonStyle = buttonStyleOverrides?.let { buttonStyle.override(it) } ?: buttonStyle
    val finalTextStyle = textStyleOverrides?.let { textStyle.override(it) } ?: textStyle

    ProvideTextStyle(finalTextStyle.textStyle) {
        AppButton(
            modifier = modifier,
            onClick = onClick,
            enabled = enabled,
            style = finalButtonStyle,
            leading = leading,
            trailing = trailing,
            content = content ?: { AppButtonDefaults.DefaultButtonLabel(text = text, style = finalTextStyle) }
        )
    }
}
