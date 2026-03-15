package com.universall.appcore.ui.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Composable
fun AppText(
    text: String,
    style: TextStyle,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    styleOverride: (TextStyleOverrides.Builder.() -> Unit)? = null,
    layoutOverride: (TextLayoutOptions.Builder.() -> Unit)? = null
) {
    val styleOverrides = if (styleOverride == null) TextStyleOverrides() else TextStyleOverrides.Builder().apply(styleOverride).build()
    val layoutOverrides = if (layoutOverride == null) TextLayoutOptions() else TextLayoutOptions.Builder().apply(layoutOverride).build()

    Text(
        text = text,
        modifier = modifier,
        color = color,
        style = styleOverrides.applyTo(style),
        textAlign = layoutOverrides.textAlign,
        overflow = layoutOverrides.overflow,
        softWrap = layoutOverrides.softWrap,
        maxLines = layoutOverrides.maxLines,
        minLines = layoutOverrides.minLines,
        onTextLayout = layoutOverrides.onTextLayout
    )
}
