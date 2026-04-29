package com.universall.appcore.appcore.ui.theme.locals

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Spacing(
    val xxxs: Dp = 2.dp,
    val xxs: Dp = 4.dp,
    val xs: Dp = 8.dp,
    val sm: Dp = 12.dp,
    val md: Dp = 16.dp,
    val lg: Dp = 24.dp,
    val xl: Dp = 32.dp,
    val xxl: Dp = 40.dp,
    val xxxl: Dp = 48.dp,

    val selectionSpacing: Dp,
    val screenPadding: Dp
) {
    companion object {
        fun default(): Spacing = Spacing(
            xxxs = 2.dp,
            xxs = 4.dp,
            xs = 8.dp,
            sm = 12.dp,
            md = 16.dp,
            lg = 24.dp,
            xl = 32.dp,
            xxl = 40.dp,
            xxxl = 48.dp,
            selectionSpacing = 16.dp,
            screenPadding = 16.dp
        )
    }
}

val LocalSpacing = staticCompositionLocalOf<Spacing> {
    error("Spacing not provided")
}
