package com.universall.appcore.appcore.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.universall.appcore.appcore.ui.theme.locals.Dimens
import com.universall.appcore.appcore.ui.theme.locals.ExtraColors
import com.universall.appcore.appcore.ui.theme.locals.Gradients
import com.universall.appcore.appcore.ui.theme.locals.LocalDimens
import com.universall.appcore.appcore.ui.theme.locals.LocalExtraColors
import com.universall.appcore.appcore.ui.theme.locals.LocalGradients
import com.universall.appcore.appcore.ui.theme.locals.LocalShapes
import com.universall.appcore.appcore.ui.theme.locals.LocalSpacing
import com.universall.appcore.appcore.ui.theme.locals.Shapes
import com.universall.appcore.appcore.ui.theme.locals.Spacing


val LightColorScheme = lightColorScheme(
    primary = AppColors.Light.Primary,
    onPrimary = AppColors.Light.OnPrimary,
    primaryContainer = AppColors.Light.PrimaryVariant,
    secondary = AppColors.Light.Secondary,
    onSecondary = AppColors.Light.OnSecondary,
    background = AppColors.Light.Background,
    onBackground = AppColors.Light.OnBackground,
    surface = AppColors.Light.Surface,
    onSurface = AppColors.Light.OnSurface,
    surfaceVariant = AppColors.Light.SurfaceVariant,
    outline = AppColors.Light.Outline,
    error = AppColors.Light.Error,
    onError = AppColors.Light.OnError
)

val LightExtraColors = ExtraColors(
    muted = AppColors.Light.Muted,
    border = AppColors.Light.Outline,
    delete = AppColors.Light.Error,
    success = AppColors.Light.Success,
    info = AppColors.Light.Info,
    onSuccess = AppColors.Light.OnSuccess,
    onInfo = AppColors.Light.OnInfo
)

val DarkColorScheme = darkColorScheme(
    primary = AppColors.Dark.Primary,
    onPrimary = AppColors.Dark.OnPrimary,
    primaryContainer = AppColors.Dark.PrimaryVariant,
    secondary = AppColors.Dark.Secondary,
    onSecondary = AppColors.Dark.OnSecondary,
    background = AppColors.Dark.Background,
    onBackground = AppColors.Dark.OnBackground,
    surface = AppColors.Dark.Surface,
    onSurface = AppColors.Dark.OnSurface,
    surfaceVariant = AppColors.Dark.SurfaceVariant,
    outline = AppColors.Dark.Outline,
    error = AppColors.Dark.Error,
    onError = AppColors.Dark.OnError
)

val DarkExtraColors = ExtraColors(
    muted = AppColors.Dark.Muted,
    border = AppColors.Dark.Outline,
    delete = AppColors.Dark.Error,
    success = AppColors.Dark.Success,
    info = AppColors.Dark.Info,
    onSuccess = AppColors.Dark.OnSuccess,
    onInfo = AppColors.Dark.OnInfo
)

@Composable
fun AppCoreTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val extraColors = when {
        darkTheme -> DarkExtraColors
        else -> LightExtraColors
    }

    val dimens = Dimens.Companion.default()
    val shapes = Shapes.Companion.default(dimens)
    val spacing = Spacing.Companion.default()
    val gradients = Gradients.Companion.default()

    CompositionLocalProvider(
        LocalDimens provides dimens,
        LocalShapes provides shapes,
        LocalSpacing provides spacing,
        LocalExtraColors provides extraColors,
        LocalGradients provides gradients
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = AppTypography,
            content = content
        )
    }
}
