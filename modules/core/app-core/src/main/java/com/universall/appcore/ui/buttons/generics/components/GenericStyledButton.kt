package com.universall.appcore.ui.buttons.generics.components

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.universall.appcore.ui.buttons.AppButton
import com.universall.appcore.ui.buttons.AppButtonStyle
import com.universall.appcore.ui.buttons.generics.AppButtonDefaults.DefaultButtonLabel
import com.universall.appcore.ui.buttons.override
import com.universall.appcore.ui.text.AppTextStyle
import com.universall.appcore.ui.text.generics.AppTextDefaults
import com.universall.appcore.ui.text.override

@Composable
fun GenericStyledButton(
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
            onClick = onClick,
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .then(modifier),
            enabled = enabled,
            style = finalButtonStyle,
            leading = leading,
            trailing = trailing,
            content = content ?: { DefaultButtonLabel(text = text, style = finalTextStyle) }
        )
    }
}
