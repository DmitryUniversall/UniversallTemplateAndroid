package com.universall.appcore.ui.buttons.generics.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.universall.appcore.ui.buttons.AppButtonStyle
import com.universall.appcore.ui.buttons.generics.AppButtonDefaults
import com.universall.appcore.ui.buttons.override
import com.universall.appcore.ui.text.AppTextStyle
import com.universall.appcore.ui.text.generics.AppTextDefaults
import com.universall.appcore.ui.text.override
import com.universall.appcore.ui.theme.locals.Locals

@Composable
fun TextButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String? = null,
    textColor: Color? = null,
    enabled: Boolean = true,
    leading: (@Composable (() -> Unit))? = null,
    trailing: (@Composable (() -> Unit))? = null,
    buttonStyleOverrides: (AppButtonStyle.Builder.() -> Unit)? = null,
    textStyle: AppTextStyle = AppTextDefaults.headlineSmallStyle(),
    textStyleOverrides: (AppTextStyle.Builder.() -> Unit)? = null,
    content: (@Composable RowScope.() -> Unit)? = null
) {
    val shapes = Locals.shapes

    CompositionLocalProvider(
        LocalContentColor provides (textColor ?: Color.Unspecified)
    ) {
        GenericStyledButton(
            modifier = modifier.wrapContentSize(unbounded = true),  // TODO: WTF?
            onClick = onClick,
            text = text,
            enabled = enabled,
            leading = leading,
            trailing = trailing,
            buttonStyleOverrides = buttonStyleOverrides,
            textStyleOverrides = textStyleOverrides,
            content = content,
            textStyle = textStyle.override {
                textDecoration = TextDecoration.Underline
                maxLines = 1
            },
            buttonStyle = AppButtonDefaults.text().override {
                minWidth = 0.dp
                minHeight = 0.dp
                contentPadding = PaddingValues(0.dp)
                shape = shapes.rectangle
            }
        )
    }
}
