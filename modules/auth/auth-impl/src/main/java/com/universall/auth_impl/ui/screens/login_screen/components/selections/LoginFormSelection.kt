package com.universall.auth_impl.ui.screens.login_screen.components.selections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.universall.appcore.ui.fields.fields.AppOutlinedField
import com.universall.appcore.ui.fields.fields.generics.PasswordOutlinedField
import com.universall.appcore.ui.fields.state.generics.TextFieldState
import com.universall.appcore.ui.resources.ResourceState
import com.universall.appcore.ui.resources.isFetching
import com.universall.appcore.ui.theme.locals.Locals
import com.universall.auth_impl.R
import com.universall.auth_impl.ui.screens.login_screen.LoginScreenUIIntent

@Composable
internal fun LoginFormSelection(
    onIntent: (LoginScreenUIIntent) -> Unit,
    loginRequestState: ResourceState<Unit>,
    loginFieldState: TextFieldState,
    passwordFieldState: TextFieldState
) {
    val shapes = Locals.shapes
    val spacing = Locals.spacing

    val fieldShape = shapes.roundedSM

    val focusManager = LocalFocusManager.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(spacing.sm)
    ) {
        AppOutlinedField(
            modifier = Modifier.fillMaxWidth(),
            shape = fieldShape,
            label = stringResource(R.string.login_field),

            state = loginFieldState,
            enabled = loginFieldState.enabled && !loginRequestState.isFetching,
            onValueChange = { onIntent(LoginScreenUIIntent.Input.InputLogin(it)) },
            onUnfocused = { onIntent(LoginScreenUIIntent.Validate.ValidateLoginField) },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )

        PasswordOutlinedField(
            modifier = Modifier.fillMaxWidth(),
            shape = fieldShape,
            label = stringResource(R.string.password_field),

            state = passwordFieldState,
            enabled = loginFieldState.enabled && !loginRequestState.isFetching,
            onValueChange = { onIntent(LoginScreenUIIntent.Input.InputPassword(it)) },
            onUnfocused = { onIntent(LoginScreenUIIntent.Validate.ValidatePasswordField) },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
        )
    }
}
