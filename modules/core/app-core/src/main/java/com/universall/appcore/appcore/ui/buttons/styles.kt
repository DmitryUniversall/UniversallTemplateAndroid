package com.universall.appcore.appcore.ui.buttons

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.universall.appcore.base.ui.buttons.AppButtonColors
import com.universall.appcore.base.ui.buttons.AppButtonStyle
import com.universall.appcore.appcore.ui.utils.muted
import com.universall.appcore.base.ui.text.AppText
import com.universall.appcore.base.ui.text.AppTextStyle
import com.universall.appcore.appcore.ui.theme.locals.Locals

object AppButtonDefaults {
    private val DefaultShape = RoundedCornerShape(12.dp)

    @Composable
    internal fun DefaultButtonLabel(
        text: String?,
        style: AppTextStyle
    ) {
        text?.let {
            AppText(
                text = text,
                style = style,
                color = Color.Unspecified
            )
        }
    }

    @Composable
    fun primary(): AppButtonStyle {
        val colors = MaterialTheme.colorScheme
        val spacing = Locals.spacing

        val buttonColors = AppButtonColors.Builder(
            container = colors.primary,
            content = colors.onPrimary,
            containerPressed = colors.primary.muted(alpha = 0.88f),
            contentPressed = colors.onPrimary,
            containerDisabled = colors.surfaceVariant,
            contentDisabled = colors.onSurfaceVariant.muted(alpha = 0.5f),
            border = null,
            borderPressed = null,
            borderDisabled = null
        ).build()

        return AppButtonStyle.Builder(
            shape = DefaultShape,
            minWidth = 96.dp,
            minHeight = 48.dp,
            contentPadding = PaddingValues(horizontal = spacing.md, vertical = spacing.sm),
            elevation = 4.dp,
            pressedElevation = 2.dp,
            borderWidth = 0.dp,
            colors = buttonColors
        ).build()
    }

    @Composable
    fun primaryOutlined(): AppButtonStyle {
        val colors = MaterialTheme.colorScheme
        val spacing = Locals.spacing

        val buttonColors = AppButtonColors.Builder(
            container = Color.Transparent,
            content = colors.primary,
            containerPressed = colors.primary.muted(alpha = 0.1f),
            contentPressed = colors.primary,
            containerDisabled = Color.Transparent,
            contentDisabled = colors.onSurfaceVariant.muted(alpha = 0.5f),
            border = colors.primary,
            borderPressed = colors.primary,
            borderDisabled = colors.outline.muted(alpha = 0.25f)
        ).build()

        return AppButtonStyle(
            shape = DefaultShape,
            minWidth = 96.dp,
            minHeight = 48.dp,
            contentPadding = PaddingValues(horizontal = spacing.md, vertical = spacing.sm),
            elevation = 0.dp,
            pressedElevation = 0.dp,
            borderWidth = 1.dp,
            colors = buttonColors
        )
    }

    @Composable
    fun secondary(): AppButtonStyle {
        val colors = MaterialTheme.colorScheme
        val spacing = Locals.spacing

        val buttonColors = AppButtonColors.Builder(
            container = colors.secondary,
            content = colors.onSecondary,
            containerPressed = colors.secondary.muted(alpha = 0.88f),
            contentPressed = colors.onSecondary,
            containerDisabled = colors.surfaceVariant,
            contentDisabled = colors.onSurfaceVariant.muted(alpha = 0.5f),
            border = Color.Transparent,
            borderPressed = Color.Transparent,
            borderDisabled = Color.Transparent
        ).build()

        return AppButtonStyle(
            shape = DefaultShape,
            minWidth = 92.dp,
            minHeight = 48.dp,
            contentPadding = PaddingValues(horizontal = spacing.md, vertical = spacing.sm),
            elevation = 2.dp,
            pressedElevation = 0.dp,
            borderWidth = 0.dp,
            colors = buttonColors
        )
    }

    @Composable
    fun secondaryOutlined(): AppButtonStyle {
        val colors = MaterialTheme.colorScheme
        val spacing = Locals.spacing

        val buttonColors = AppButtonColors.Builder(
            container = Color.Transparent,
            content = colors.secondary,
            containerPressed = colors.secondary.muted(alpha = 0.1f),
            contentPressed = colors.secondary,
            containerDisabled = Color.Transparent,
            contentDisabled = colors.onSurfaceVariant.muted(alpha = 0.5f),
            border = colors.secondary,
            borderPressed = colors.secondary,
            borderDisabled = colors.outline.muted(alpha = 0.25f)
        ).build()

        return AppButtonStyle(
            shape = DefaultShape,
            minWidth = 92.dp,
            minHeight = 48.dp,
            contentPadding = PaddingValues(horizontal = spacing.md, vertical = spacing.sm),
            elevation = 0.dp,
            pressedElevation = 0.dp,
            borderWidth = 1.dp,
            colors = buttonColors
        )
    }

    @Composable
    fun tertiary(): AppButtonStyle {
        val colors = MaterialTheme.colorScheme
        val spacing = Locals.spacing

        val buttonColors = AppButtonColors.Builder(
            container = Color.Transparent,
            content = colors.primary,
            containerPressed = colors.primary.muted(alpha = 0.1f),
            contentPressed = colors.primary,
            containerDisabled = Color.Transparent,
            contentDisabled = colors.onSurfaceVariant.muted(alpha = 0.5f)
        ).build()

        return AppButtonStyle.Builder(
            shape = DefaultShape,
            minWidth = 80.dp,
            minHeight = 48.dp,
            contentPadding = PaddingValues(horizontal = spacing.md, vertical = spacing.sm),
            elevation = 0.dp,
            pressedElevation = 0.dp,
            borderWidth = 0.dp,
            colors = buttonColors
        ).build()
    }

    @Composable
    fun text(): AppButtonStyle {
        val colors = MaterialTheme.colorScheme

        val buttonColors = AppButtonColors.Builder(
            container = Color.Transparent,
            content = colors.primary,
            containerPressed = colors.primary.muted(alpha = 0.1f),
            contentPressed = colors.primary,
            containerDisabled = Color.Transparent,
            contentDisabled = colors.onSurfaceVariant.muted(alpha = 0.5f)
        ).build()

        return AppButtonStyle.Builder(
            shape = DefaultShape,
            minWidth = 64.dp,
            minHeight = 48.dp,
            contentPadding = PaddingValues(0.dp),
            elevation = 0.dp,
            pressedElevation = 0.dp,
            borderWidth = 0.dp,
            colors = buttonColors
        ).build()
    }

    @Composable
    fun icon(): AppButtonStyle {
        val colors = MaterialTheme.colorScheme
        val spacing = Locals.spacing

        val buttonColors = AppButtonColors.Builder(
            container = colors.primary,
            content = colors.onPrimary,
            containerPressed = colors.primary.muted(alpha = 0.88f),
            contentPressed = colors.onPrimary,
            containerDisabled = colors.surfaceVariant,
            contentDisabled = colors.onSurfaceVariant.muted(alpha = 0.5f)
        ).build()

        return AppButtonStyle.Builder(
            shape = DefaultShape,
            minWidth = 32.dp,
            minHeight = 32.dp,
            contentPadding = PaddingValues(all = spacing.sm),
            elevation = 2.dp,
            pressedElevation = 1.dp,
            borderWidth = 0.dp,
            colors = buttonColors
        ).build()
    }
}