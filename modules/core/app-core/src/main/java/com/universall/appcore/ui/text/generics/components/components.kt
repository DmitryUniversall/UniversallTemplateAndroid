package com.universall.appcore.ui.text.generics.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.universall.appcore.ui.text.AppText
import com.universall.appcore.ui.text.AppTextStyle
import com.universall.appcore.ui.text.generics.AppTextDefaults


@Composable
fun TextDisplayLarge(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    styleOverride: (AppTextStyle.Builder.() -> Unit)? = null
) = AppText(
    text = text,
    style = AppTextDefaults.displayLargeStyle(),
    modifier = modifier,
    color = color,
    styleOverrides = styleOverride
)

@Composable
fun TextDisplayMedium(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    styleOverride: (AppTextStyle.Builder.() -> Unit)? = null
) = AppText(
    text = text,
    style = AppTextDefaults.displayMediumStyle(),
    modifier = modifier,
    color = color,
    styleOverrides = styleOverride
)

@Composable
fun TextDisplaySmall(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    styleOverride: (AppTextStyle.Builder.() -> Unit)? = null
) = AppText(
    text = text,
    style = AppTextDefaults.displaySmallStyle(),
    modifier = modifier,
    color = color,
    styleOverrides = styleOverride
)

@Composable
fun TextHeadlineLarge(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    styleOverride: (AppTextStyle.Builder.() -> Unit)? = null
) = AppText(
    text = text,
    style = AppTextDefaults.headlineLargeStyle(),
    modifier = modifier,
    color = color,
    styleOverrides = styleOverride
)

@Composable
fun TextHeadlineMedium(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    styleOverride: (AppTextStyle.Builder.() -> Unit)? = null
) = AppText(
    text = text,
    style = AppTextDefaults.headlineMediumStyle(),
    modifier = modifier,
    color = color,
    styleOverrides = styleOverride
)

@Composable
fun TextHeadlineSmall(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    styleOverride: (AppTextStyle.Builder.() -> Unit)? = null
) = AppText(
    text = text,
    style = AppTextDefaults.headlineSmallStyle(),
    modifier = modifier,
    color = color,
    styleOverrides = styleOverride
)

@Composable
fun TextTitleLarge(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    styleOverride: (AppTextStyle.Builder.() -> Unit)? = null
) = AppText(
    text = text,
    style = AppTextDefaults.titleLargeStyle(),
    modifier = modifier,
    color = color,
    styleOverrides = styleOverride
)

@Composable
fun TextTitleMedium(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    styleOverride: (AppTextStyle.Builder.() -> Unit)? = null
) = AppText(
    text = text,
    style = AppTextDefaults.titleMediumStyle(),
    modifier = modifier,
    color = color,
    styleOverrides = styleOverride
)

@Composable
fun TextTitleSmall(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    styleOverride: (AppTextStyle.Builder.() -> Unit)? = null
) = AppText(
    text = text,
    style = AppTextDefaults.titleSmallStyle(),
    modifier = modifier,
    color = color,
    styleOverrides = styleOverride
)


@Composable
fun TextBodyLarge(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    styleOverride: (AppTextStyle.Builder.() -> Unit)? = null
) = AppText(
    text = text,
    style = AppTextDefaults.bodyLargeStyle(),
    modifier = modifier,
    color = color,
    styleOverrides = styleOverride
)

@Composable
fun TextBodyMedium(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    styleOverride: (AppTextStyle.Builder.() -> Unit)? = null
) = AppText(
    text = text,
    style = AppTextDefaults.bodyMediumStyle(),
    modifier = modifier,
    color = color,
    styleOverrides = styleOverride
)

@Composable
fun TextBodySmall(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    styleOverride: (AppTextStyle.Builder.() -> Unit)? = null
) = AppText(
    text = text,
    style = AppTextDefaults.bodySmallStyle(),
    modifier = modifier,
    color = color,
    styleOverrides = styleOverride
)

@Composable
fun TextLabelLarge(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    styleOverride: (AppTextStyle.Builder.() -> Unit)? = null
) = AppText(
    text = text,
    style = AppTextDefaults.labelLargeStyle(),
    modifier = modifier,
    color = color,
    styleOverrides = styleOverride
)

@Composable
fun TextLabelMedium(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    styleOverride: (AppTextStyle.Builder.() -> Unit)? = null
) = AppText(
    text = text,
    style = AppTextDefaults.labelMediumStyle(),
    modifier = modifier,
    color = color,
    styleOverrides = styleOverride
)

@Composable
fun TextLabelSmall(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    styleOverride: (AppTextStyle.Builder.() -> Unit)? = null
) = AppText(
    text = text,
    style = AppTextDefaults.labelSmallStyle(),
    modifier = modifier,
    color = color,
    styleOverrides = styleOverride
)
