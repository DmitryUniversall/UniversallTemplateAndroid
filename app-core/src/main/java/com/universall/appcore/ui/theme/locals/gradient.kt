package com.universall.appcore.ui.theme.locals

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

data class Gradients(
    val primaryRightToLeft: Brush,
    val primaryToCenter: Brush
) {
    companion object {
        fun default(): Gradients = Gradients(
            primaryRightToLeft = Brush.linearGradient(
                colors = listOf(
                    Color(0xFFFFA400),
                    Color(0xFFFFAB07),
                    Color(0xFFFFAB07)
                ),
                start = Offset.Zero,
                end = Offset.Infinite
            ),
            primaryToCenter = Brush.linearGradient(
                colors = listOf(
                    Color(0xFFFFA400),
                    Color(0xFFFFAB07),
                    Color(0xFFFFAB07)
                ),
                start = Offset.Zero,
                end = Offset.Infinite
            )
        )
    }
}

val LocalGradients = staticCompositionLocalOf<Gradients> {
    error("Gradients was not provided")
}
