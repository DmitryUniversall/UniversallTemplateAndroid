package com.universall.appcore.ui.theme.locals

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class ExtraColors(
    val muted: Color,
    val border: Color,
    val delete: Color,
    val success: Color,
    val info: Color,

    val onSuccess: Color,
    val onInfo: Color
)

val LocalExtraColors = staticCompositionLocalOf<ExtraColors> {
    error("ExtraColors not provided")
}
