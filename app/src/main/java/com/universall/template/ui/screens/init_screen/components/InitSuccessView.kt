package com.universall.template.ui.screens.init_screen.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.universall.appcore.ui.text.TextHeadlineMedium
import com.universall.template.R

@Composable
fun InitSuccessView() {
    TextHeadlineMedium(
        text = stringResource(R.string.init_success),
        styleOverride = {
            fontWeight = FontWeight.Bold
        }
    )
}
