package com.universall.init_impl.ui.screens.init_screen.components.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.universall.appcore.appcore.ui.buttons.components.TertiaryButton
import com.universall.appcore.appcore.ui.utils.muted
import com.universall.appcore.appcore.ui.state.RequestState
import com.universall.appcore.appcore.ui.text.components.TextBodyLarge
import com.universall.appcore.appcore.ui.text.components.TextBodyMedium
import com.universall.appcore.appcore.ui.text.components.TextDisplaySmall
import com.universall.appcore.appcore.ui.theme.locals.Locals
import com.universall.appcore.appcore.ui.utils.asStringOrIfEmpty
import com.universall.init_impl.R
import com.universall.init_impl.ui.screens.init_screen.InitScreenUIIntent

@Composable
internal fun InitScreenErrorView(
    onIntent: (InitScreenUIIntent) -> Unit,
    restoreAuthRequestErrorState: RequestState.Error<Unit>
) {
    val colors = MaterialTheme.colorScheme
    val spacing = Locals.spacing

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(spacing.md)
    ) {
        TextDisplaySmall(
            text = stringResource(R.string.error_occurred),
            styleOverride = {
                fontWeight = FontWeight.Bold
            }
        )

        TextBodyLarge(
            text = restoreAuthRequestErrorState.errorMessage.asStringOrIfEmpty { stringResource(R.string.unknown_error) },
            styleOverride = {
                textAlign = TextAlign.Center
            }
        )

        TextBodyMedium(
            text = stringResource(R.string.pull_to_refresh_action),
            color = colors.onBackground.muted()
        )

        TertiaryButton(
            text = stringResource(R.string.logout_action),
            onClick = { onIntent(InitScreenUIIntent.LocalLogout) }
        )
    }
}
