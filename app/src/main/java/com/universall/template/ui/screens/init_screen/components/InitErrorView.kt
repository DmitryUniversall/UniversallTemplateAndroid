package com.universall.template.ui.screens.init_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.universall.appcore.ui.muted
import com.universall.appcore.ui.state.ResourceState
import com.universall.appcore.ui.text.TextBodySmall
import com.universall.appcore.ui.text.TextHeadlineMedium
import com.universall.appcore.ui.theme.locals.Locals
import com.universall.template.R
import com.universall.template.ui.screens.init_screen.InitScreenViewModel

@Composable
fun InitErrorView(
    viewModel: InitScreenViewModel,
    state: ResourceState.Error<*>
) {
    val colors = MaterialTheme.colorScheme
    val spacing = Locals.spacing

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(spacing.sm)
    ) {
        TextHeadlineMedium(
            text = "${stringResource(R.string.init_error)}: ${state.errorMessage}",
            styleOverride = {
                fontWeight = FontWeight.Bold
            }
        )

        TextBodySmall(
            text = stringResource(R.string.init_pull_to_refresh),
            color = colors.onBackground.muted()
        )

//        PrimaryGenericButton(
//            text = stringResource(R.string.init_logout),
//            onClick = {
//                viewModel.onIntent(InitIntent.LocalLogout)
//            }
//        )
    }
}
