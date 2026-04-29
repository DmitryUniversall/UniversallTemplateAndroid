package com.universall.appcore.base.ui.buttons

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.universall.appcore.appcore.ui.theme.locals.Locals
import com.universall.appcore.appcore.ui.utils.withShapedBackground

@Composable
internal fun AppButton(
    modifier: Modifier,
    onClick: () -> Unit,
    enabled: Boolean,
    style: AppButtonStyle,
    leading: (@Composable (() -> Unit))?,
    trailing: (@Composable (() -> Unit))?,
    content: @Composable RowScope.() -> Unit
) {
    val spacing = Locals.spacing

    val interactionSource = remember { MutableInteractionSource() }
    val pressed = interactionSource.collectIsPressedAsState().value

    val containerColor = animateColorAsState(
        targetValue = when {
            !enabled -> style.colors.containerDisabled
            pressed -> style.colors.containerPressed
            else -> style.colors.container
        },
        label = "containerColor"
    )

    val contentColor = animateColorAsState(
        targetValue = when {
            !enabled -> style.colors.contentDisabled
            pressed -> style.colors.contentPressed
            else -> style.colors.content
        },
        label = "contentColor"
    )

    val borderColor = animateColorAsState(
        targetValue = when {
            !enabled -> style.colors.borderDisabled ?: style.colors.border ?: Color.Transparent
            pressed -> style.colors.borderPressed ?: style.colors.border ?: Color.Transparent
            else -> style.colors.border ?: Color.Transparent
        },
        label = "borderColor"
    )

    val shadowElevation = animateDpAsState(
        targetValue = when {
            !enabled -> 0.dp
            pressed -> style.pressedElevation
            else -> style.elevation
        },
        label = "shadowElevation"
    )

    val clickAction = rememberUpdatedState(onClick)

    val shape = style.shape

    Box(
        modifier = modifier
            .sizeIn(minWidth = style.minWidth, minHeight = style.minHeight)
            .semantics(mergeDescendants = true) { role = Role.Button }
            .shadow(
                elevation = shadowElevation.value,
                shape = style.shape,
                clip = false
            )
            .withShapedBackground(color = containerColor.value, shape = shape)
            .then(
                if (style.borderWidth > 0.dp) {
                    Modifier.border(
                        BorderStroke(style.borderWidth, borderColor.value),
                        shape = shape
                    )
                } else {
                    Modifier
                }
            )
            .clickable(
                enabled = enabled,
                interactionSource = interactionSource,
                onClick = { clickAction.value() }
            )
            .padding(style.contentPadding)
    ) {
        CompositionLocalProvider(LocalContentColor provides contentColor.value) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (leading != null) {
                    leading()
                    Spacer(modifier = Modifier.width(spacing.xs))
                }

                content()

                if (trailing != null) {
                    Spacer(modifier = Modifier.width(spacing.xs))
                    trailing()
                }
            }
        }
    }
}
