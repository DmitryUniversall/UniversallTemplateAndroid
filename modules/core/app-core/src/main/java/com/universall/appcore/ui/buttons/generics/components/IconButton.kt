package com.universall.appcore.ui.buttons.generics.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.universall.appcore.ui.buttons.AppButton
import com.universall.appcore.ui.buttons.AppButtonStyle
import com.universall.appcore.ui.buttons.generics.AppButtonDefaults
import com.universall.appcore.ui.buttons.override

@Composable
fun IconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    imageVector: ImageVector,
    contentDescription: String? = null,
    tint: Color? = null,
    enabled: Boolean = true,
    leading: (@Composable (() -> Unit))? = null,
    trailing: (@Composable (() -> Unit))? = null,
    buttonStyleOverrides: (AppButtonStyle.Builder.() -> Unit)? = null,
    content: (@Composable RowScope.() -> Unit)? = null
) {
    val baseButtonStyle = AppButtonDefaults.icon()
    val finalButtonStyle = buttonStyleOverrides?.let { baseButtonStyle.override(it) } ?: baseButtonStyle

    AppButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        style = finalButtonStyle,
        leading = leading,
        trailing = trailing,
        content = content ?: {
            Icon(
                modifier = Modifier
                    .fillMaxSize(),
                imageVector = imageVector,
                contentDescription = contentDescription,
                tint = tint ?: LocalContentColor.current
            )
        }
    )
}
