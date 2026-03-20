package com.universall.template.ui.screens.init_screen.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InitLoadingView() {
    CircularProgressIndicator(
        modifier = Modifier.size(64.dp)
    )
}
