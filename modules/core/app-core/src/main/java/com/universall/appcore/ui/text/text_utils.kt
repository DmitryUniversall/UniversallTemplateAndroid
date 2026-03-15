package com.universall.appcore.ui.text

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
fun TextDisplayLarge(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    styleOverride: (TextStyleOverrides.Builder.() -> Unit)? = null,
    layoutOverride: (TextLayoutOptions.Builder.() -> Unit)? = null
) = AppText(
    text = text,
    style = MaterialTheme.typography.displayLarge,
    modifier = modifier,
    color = color,
    styleOverride = styleOverride,
    layoutOverride = layoutOverride
)

@Composable
fun TextDisplayMedium(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    styleOverride: (TextStyleOverrides.Builder.() -> Unit)? = null,
    layoutOverride: (TextLayoutOptions.Builder.() -> Unit)? = null
) = AppText(
    text = text,
    style = MaterialTheme.typography.displayMedium,
    modifier = modifier,
    color = color,
    styleOverride = styleOverride,
    layoutOverride = layoutOverride
)

@Composable
fun TextDisplaySmall(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    styleOverride: (TextStyleOverrides.Builder.() -> Unit)? = null,
    layoutOverride: (TextLayoutOptions.Builder.() -> Unit)? = null
) = AppText(
    text = text,
    style = MaterialTheme.typography.displaySmall,
    modifier = modifier,
    color = color,
    styleOverride = styleOverride,
    layoutOverride = layoutOverride
)

@Composable
fun TextHeadlineLarge(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    styleOverride: (TextStyleOverrides.Builder.() -> Unit)? = null,
    layoutOverride: (TextLayoutOptions.Builder.() -> Unit)? = null
) = AppText(
    text = text,
    style = MaterialTheme.typography.headlineLarge,
    modifier = modifier,
    color = color,
    styleOverride = styleOverride,
    layoutOverride = layoutOverride
)

@Composable
fun TextHeadlineMedium(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    styleOverride: (TextStyleOverrides.Builder.() -> Unit)? = null,
    layoutOverride: (TextLayoutOptions.Builder.() -> Unit)? = null
) = AppText(
    text = text,
    style = MaterialTheme.typography.headlineMedium,
    modifier = modifier,
    color = color,
    styleOverride = styleOverride,
    layoutOverride = layoutOverride
)

@Composable
fun TextHeadlineSmall(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    styleOverride: (TextStyleOverrides.Builder.() -> Unit)? = null,
    layoutOverride: (TextLayoutOptions.Builder.() -> Unit)? = null
) = AppText(
    text = text,
    style = MaterialTheme.typography.headlineSmall,
    modifier = modifier,
    color = color,
    styleOverride = styleOverride,
    layoutOverride = layoutOverride
)


@Composable
fun TextBodyLarge(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    styleOverride: (TextStyleOverrides.Builder.() -> Unit)? = null,
    layoutOverride: (TextLayoutOptions.Builder.() -> Unit)? = null
) {

    AppText(
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier,
        color = color,
        styleOverride = styleOverride,
        layoutOverride = layoutOverride
    )
}

@Composable
fun TextBodyMedium(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    styleOverride: (TextStyleOverrides.Builder.() -> Unit)? = null,
    layoutOverride: (TextLayoutOptions.Builder.() -> Unit)? = null
) = AppText(
    text = text,
    style = MaterialTheme.typography.bodyMedium,
    modifier = modifier,
    color = color,
    styleOverride = styleOverride,
    layoutOverride = layoutOverride
)

@Composable
fun TextBodySmall(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    styleOverride: (TextStyleOverrides.Builder.() -> Unit)? = null,
    layoutOverride: (TextLayoutOptions.Builder.() -> Unit)? = null
) = AppText(
    text = text,
    style = MaterialTheme.typography.bodySmall,
    modifier = modifier,
    color = color,
    styleOverride = styleOverride,
    layoutOverride = layoutOverride
)
