package com.universall.auth_impl.ui.screens.register_screen.components.selections

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.universall.appcore.ui.buttons.generics.components.PrimaryButton
import com.universall.appcore.ui.resources.ResourceState
import com.universall.appcore.ui.resources.isFetching
import com.universall.appcore.ui.text.AppText
import com.universall.appcore.ui.theme.locals.Locals
import com.universall.auth_impl.R
import com.universall.auth_impl.ui.screens.register_screen.RegisterScreenUIIntent

@Composable
internal fun RegisterSendButtonSelection(
    onIntent: (RegisterScreenUIIntent) -> Unit,
    isSendButtonEnabled: Boolean,
    registerRequestState: ResourceState<Unit>
) {
    val spacing = Locals.spacing

    PrimaryButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = spacing.screenPadding),
        enabled = isSendButtonEnabled,
        onClick = { onIntent(RegisterScreenUIIntent.SendRegister) }
    ) {
        if (registerRequestState.isFetching) {
            CircularProgressIndicator(
                modifier = Modifier.size(16.dp),
                strokeWidth = 2.dp
            )
        } else {
            AppText(text = stringResource(R.string.register_action))
        }
    }
}
