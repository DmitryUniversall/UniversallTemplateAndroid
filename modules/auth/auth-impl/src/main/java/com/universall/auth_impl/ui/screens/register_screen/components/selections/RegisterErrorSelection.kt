package com.universall.auth_impl.ui.screens.register_screen.components.selections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.universall.appcore.ui.muted
import com.universall.appcore.ui.state.ResourceState
import com.universall.appcore.ui.state.isError
import com.universall.appcore.ui.state.whenHasAnyError
import com.universall.appcore.ui.text.generics.components.TextBodyMedium
import com.universall.appcore.utils.asStringOrIfEmpty
import com.universall.auth_impl.R

@Composable
internal fun RegisterErrorSelection(
    registerRequestState: ResourceState<Unit>
) {
    val colors = MaterialTheme.colorScheme

    registerRequestState.whenHasAnyError { error ->
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            TextBodyMedium(
                text = error.errorMessage.asStringOrIfEmpty { stringResource(R.string.unknown_auth_error) },
                color = colors.error.muted(if (registerRequestState.isError) 1f else 0.7f),
                styleOverride = { textAlign = TextAlign.Center }
            )
        }
    }
}
