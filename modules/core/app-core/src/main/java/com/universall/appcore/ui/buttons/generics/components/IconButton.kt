package com.universall.appcore.ui.buttons.generics.components

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
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
    buttonStyle: AppButtonStyle = AppButtonDefaults.icon(),
    buttonStyleOverrides: (AppButtonStyle.Builder.() -> Unit)? = null,
    content: (@Composable RowScope.() -> Unit)? = null
) {
    val finalButtonStyle = buttonStyleOverrides?.let { buttonStyle.override(it) } ?: buttonStyle

    AppButton(
        onClick = onClick,
        modifier = Modifier
            .width(IntrinsicSize.Min)
            .then(modifier),
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
