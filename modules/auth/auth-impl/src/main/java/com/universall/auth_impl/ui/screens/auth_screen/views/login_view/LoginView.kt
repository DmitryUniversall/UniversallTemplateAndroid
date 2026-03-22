package com.universall.auth_impl.ui.screens.auth_screen.views.login_view

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import com.universall.appcore.ui.fields.AppOutlinedField
import com.universall.appcore.ui.muted
import com.universall.appcore.ui.state.ResourceState
import com.universall.appcore.ui.state.isError
import com.universall.appcore.ui.state.isFetching
import com.universall.appcore.ui.state.whenHasAnyError
import com.universall.appcore.ui.text.generics.components.TextBodyLarge
import com.universall.appcore.ui.text.generics.components.TextBodyMedium
import com.universall.appcore.ui.theme.locals.Locals
import com.universall.appcore.utils.asStringOrIfEmpty
import com.universall.auth_impl.R
import com.universall.auth_impl.ui.screens.auth_screen.AuthUIIntent
import com.universall.auth_impl.ui.screens.auth_screen.AuthUIState
import com.universall.auth_impl.ui.screens.auth_screen.AuthViewModel

@Composable
fun LoginView(
    viewModel: AuthViewModel,
    input: AuthUIState.LoginInputInfo,
    loginRequestState: ResourceState<Unit>
) {
    val colors = MaterialTheme.colorScheme
    val shapes = Locals.shapes
    val spacing = Locals.spacing

    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(spacing.sm),
    ) {
        AppOutlinedField(
            modifier = Modifier.fillMaxWidth(),
            shape = shapes.roundedSM,
            label = stringResource(R.string.login_field),

            state = input.login,
            enabled = !loginRequestState.isFetching,
            onValueChange = {
                viewModel.onIntent(AuthUIIntent.InputLogin(input.copy(login = input.login.copy(value = it))))
            },
            onUnfocused = {
                viewModel.onIntent(AuthUIIntent.Validate.Login.LoginField(input.login))
            }
        )

        AppOutlinedField(
            modifier = Modifier.fillMaxWidth(),
            shape = shapes.roundedSM,
            label = stringResource(R.string.password_field),

            state = input.password,
            enabled = !loginRequestState.isFetching,
            onValueChange = {
                viewModel.onIntent(AuthUIIntent.InputLogin(input.copy(login = input.password.copy(value = it))))
            },
            onUnfocused = {
                viewModel.onIntent(AuthUIIntent.Validate.Login.PasswordField(input.password))
            }
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            TextBodyLarge(  // TODO: Use button
                text = stringResource(R.string.forgotPassword),
                color = colors.primary.muted(alpha = if (!loginRequestState.isFetching) 0.9f else 0.7f),
                styleOverride = {
                    textDecoration = TextDecoration.Underline
                }
            )
        }

        loginRequestState.whenHasAnyError { error ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                TextBodyMedium(
                    text = error.errorMessage.asStringOrIfEmpty { stringResource(R.string.unknown_auth_error) },
                    color = colors.error.muted(if (loginRequestState.isError) 1f else 0.7f),
                    styleOverride = {
                        textAlign = TextAlign.Center
                    }
                )
            }
        }
    }
}
