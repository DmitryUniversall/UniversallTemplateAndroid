package com.universall.auth_impl.ui.screens.login_screen.components.selections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.universall.appcore.appcore.ui.utils.muted
import com.universall.appcore.appcore.ui.state.RequestState
import com.universall.appcore.appcore.ui.state.isError
import com.universall.appcore.appcore.ui.state.whenHasAnyError
import com.universall.appcore.appcore.ui.text.components.TextBodyMedium
import com.universall.appcore.appcore.ui.utils.asStringOrIfEmpty
import com.universall.auth_impl.R

@Composable
internal fun LoginErrorSelection(
    loginRequestState: RequestState<Unit>
) {
    val colors = MaterialTheme.colorScheme

    loginRequestState.whenHasAnyError { error ->
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            TextBodyMedium(
                text = error.errorMessage.asStringOrIfEmpty { stringResource(R.string.unknown_auth_error) },
                color = colors.error.muted(if (loginRequestState.isError) 1f else 0.7f),
                styleOverride = { textAlign = TextAlign.Center }
            )
        }
    }
}
