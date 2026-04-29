package com.universall.appcore.appcore.ui.text

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import com.universall.appcore.base.ui.text.AppTextStyle

object AppTextDefaults {
    @Composable
    private fun baseTextStyle(textStyle: TextStyle): AppTextStyle {
        return AppTextStyle.Builder()
            .fromTextStyle(textStyle)
            .build()
    }

    @Composable
    fun displayLargeStyle(): AppTextStyle =
        baseTextStyle(MaterialTheme.typography.displayLarge)

    @Composable
    fun displayMediumStyle(): AppTextStyle =
        baseTextStyle(MaterialTheme.typography.displayMedium)

    @Composable
    fun displaySmallStyle(): AppTextStyle =
        baseTextStyle(MaterialTheme.typography.displaySmall)

    @Composable
    fun headlineLargeStyle(): AppTextStyle =
        baseTextStyle(MaterialTheme.typography.headlineLarge)

    @Composable
    fun headlineMediumStyle(): AppTextStyle =
        baseTextStyle(MaterialTheme.typography.headlineMedium)

    @Composable
    fun headlineSmallStyle(): AppTextStyle =
        baseTextStyle(MaterialTheme.typography.headlineSmall)

    @Composable
    fun titleLargeStyle(): AppTextStyle =
        baseTextStyle(MaterialTheme.typography.titleLarge)

    @Composable
    fun titleMediumStyle(): AppTextStyle =
        baseTextStyle(MaterialTheme.typography.titleMedium)

    @Composable
    fun titleSmallStyle(): AppTextStyle =
        baseTextStyle(MaterialTheme.typography.titleSmall)

    @Composable
    fun bodyLargeStyle(): AppTextStyle =
        baseTextStyle(MaterialTheme.typography.bodyLarge)

    @Composable
    fun bodyMediumStyle(): AppTextStyle =
        baseTextStyle(MaterialTheme.typography.bodyMedium)

    @Composable
    fun bodySmallStyle(): AppTextStyle =
        baseTextStyle(MaterialTheme.typography.bodySmall)

    @Composable
    fun labelLargeStyle(): AppTextStyle =
        baseTextStyle(MaterialTheme.typography.labelLarge)

    @Composable
    fun labelMediumStyle(): AppTextStyle =
        baseTextStyle(MaterialTheme.typography.labelMedium)

    @Composable
    fun labelSmallStyle(): AppTextStyle =
        baseTextStyle(MaterialTheme.typography.labelSmall)
}
