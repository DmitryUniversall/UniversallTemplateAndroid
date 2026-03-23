package com.universall.init_impl.ui.screens.init_screen.components.views

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun InitScreenLoadingView() {
    CircularProgressIndicator(
        modifier = Modifier.size(64.dp)
    )
}
