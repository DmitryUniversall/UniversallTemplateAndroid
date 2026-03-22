package com.universall.auth_impl.ui.screens.auth_screen.views.register_view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.universall.appcore.ui.fields.FieldState
import com.universall.appcore.ui.state.ResourceState
import com.universall.appcore.ui.state.isFetching
import com.universall.appcore.ui.text.AppText
import com.universall.appcore.ui.text.generics.components.TextBodySmall
import com.universall.appcore.ui.theme.locals.Locals
import com.universall.appcore.utils.isNullOrEmpty
import com.universall.auth_impl.R
import com.universall.auth_impl.ui.screens.auth_screen.AuthUIIntent
import com.universall.auth_impl.ui.screens.auth_screen.AuthUIState
import com.universall.auth_impl.ui.screens.auth_screen.AuthViewModel

@Composable
fun RegisterView(
    viewModel: AuthViewModel,
    input: AuthUIState.RegisterInputInfo,
    registerRequestState: ResourceState<Unit>
) {
    val colors = MaterialTheme.colorScheme
    val shapes = Locals.shapes
    val spacing = Locals.spacing

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(spacing.sm),
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            shape = shapes.roundedSM,
            label = { AppText(text = stringResource(R.string.login_field)) },

            value = input.login.value,
            onValueChange = { viewModel.onIntent(AuthUIIntent.InputRegister(input.copy(login = FieldState(it)))) },
            enabled = !registerRequestState.isFetching,

            isError = !input.login.errorMessage.isNullOrEmpty(),
            supportingText = if (input.login.errorMessage.isNullOrEmpty()) null else (@Composable {
                TextBodySmall(
                    text = input.login.errorMessage!!.asString(),
                    color = colors.error
                )
            })
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            shape = shapes.roundedSM,
            label = { AppText(text = stringResource(R.string.password_field)) },

            value = input.password.value,
            onValueChange = { viewModel.onIntent(AuthUIIntent.InputRegister(input.copy(password = FieldState(it)))) },
            enabled = !registerRequestState.isFetching,

            isError = !input.password.errorMessage.isNullOrEmpty(),
            supportingText = if (input.password.errorMessage.isNullOrEmpty()) null else (@Composable {
                TextBodySmall(
                    text = input.password.errorMessage!!.asString(),
                    color = colors.error
                )
            })
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            shape = shapes.roundedSM,
            label = { AppText(text = stringResource(R.string.first_name_field)) },

            value = input.firstName.value,
            onValueChange = { viewModel.onIntent(AuthUIIntent.InputRegister(input.copy(firstName = FieldState(it)))) },
            enabled = !registerRequestState.isFetching,

            isError = !input.firstName.errorMessage.isNullOrEmpty(),
            supportingText = if (input.firstName.errorMessage.isNullOrEmpty()) null else (@Composable {
                TextBodySmall(
                    text = input.firstName.errorMessage!!.asString(),
                    color = colors.error
                )
            })
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            shape = shapes.roundedSM,
            label = { AppText(text = stringResource(R.string.last_name_field)) },

            value = input.lastName.value,
            onValueChange = { viewModel.onIntent(AuthUIIntent.InputRegister(input.copy(lastName = FieldState(it)))) },
            enabled = !registerRequestState.isFetching,

            isError = !input.lastName.errorMessage.isNullOrEmpty(),
            supportingText = if (input.lastName.errorMessage.isNullOrEmpty()) null else (@Composable {
                TextBodySmall(
                    text = input.lastName.errorMessage!!.asString(),
                    color = colors.error
                )
            })
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            shape = shapes.roundedSM,
            label = { AppText(text = stringResource(R.string.last_name_field)) },

            value = input.username.value,
            onValueChange = { viewModel.onIntent(AuthUIIntent.InputRegister(input.copy(username = FieldState(it)))) },
            enabled = !registerRequestState.isFetching,

            isError = !input.username.errorMessage.isNullOrEmpty(),
            supportingText = if (input.username.errorMessage.isNullOrEmpty()) null else (@Composable {
                TextBodySmall(
                    text = input.username.errorMessage!!.asString(),
                    color = colors.error
                )
            })
        )
    }
}
