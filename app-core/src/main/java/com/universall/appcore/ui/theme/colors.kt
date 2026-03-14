package com.universall.appcore.ui.theme

import androidx.compose.ui.graphics.Color
import com.universall.appcore.ui.CoreColors

object AppColors {
    object Light {
        val Primary = Color(0xFFFFAB07)
        val PrimaryVariant = Color(0xFFF9C403)
        val OnPrimary = Color.White

        val Secondary = CoreColors.Neutral.Neutral500
        val OnSecondary = Color.White

        val Background = Color(0xFFF7F7F7)
        val Surface = Color.White
        val SurfaceVariant = Color(0xFFF1F2F4)
        val OnBackground = Color(0xFF1F1F1F)
        val OnSurface = Color(0xFF111111)

        val Muted = Color(0xFF7D7D7D)
        val Outline = Color(0xFFE6E6E6)

        val Success = Color(0xFF2E7D32)
        val Error = Color(0xFFD32F2F)
        val Info = Color(0xFF0288D1)

        val OnSuccess = Color.White
        val OnError = Color.White
        val OnInfo = Color.White
    }

    object Dark {
        val Primary = Color(0xFFFFAB07)
        val PrimaryVariant = Color(0xFFF9C403)
        val OnPrimary = Color(0xFF000000)

        val Secondary = CoreColors.Neutral.Neutral500
        val OnSecondary = Color.White

        val Background = Color(0xFF121212)
        val Surface = Color(0xFF1C1C1C)
        val SurfaceVariant = Color(0xFF262628)
        val OnBackground = Color.White
        val OnSurface = Color.White

        val Muted = Color(0xFF9E9E9E)
        val Outline = Color(0xFF2C2C2C)

        val Success = Color(0xFF66BB6A)
        val Error = Color(0xFFEF5350)
        val Info = Color(0xFF03A9F4)

        val OnSuccess = Color.White
        val OnError = Color.White
        val OnInfo = Color.White
    }
}
