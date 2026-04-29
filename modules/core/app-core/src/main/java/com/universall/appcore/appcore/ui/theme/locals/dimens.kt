package com.universall.appcore.appcore.ui.theme.locals

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimens(
    val xxs: Dp,
    val xs: Dp,
    val sm: Dp,
    val md: Dp,
    val lg: Dp,
    val xl: Dp,
    val xxl: Dp,
    val xxxl: Dp
) {
    companion object {
        fun default() = Dimens(
            xxs = 4.dp,
            xs = 8.dp,
            sm = 12.dp,
            md = 16.dp,
            lg = 24.dp,
            xl = 32.dp,
            xxl = 40.dp,
            xxxl = 48.dp
        )
    }
}

val LocalDimens = staticCompositionLocalOf<Dimens> {
    error("Dimens not provided")
}
