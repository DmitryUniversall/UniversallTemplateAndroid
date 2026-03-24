package com.universall.auth_impl.ui.screens.register_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.universall.appcore.ui.fields.state.validators.setNewValue
import com.universall.appcore.ui.fields.state.validators.validate
import com.universall.appcore.ui.fields.state.validators.validateStringLength
import com.universall.appcore.ui.resources.isFetching
import com.universall.appcore.ui.resources.toError
import com.universall.appcore.ui.resources.toLoading
import com.universall.appcore.ui.resources.toSuccess
import com.universall.appcore.utils.UIString
import com.universall.appcore.utils.logError
import com.universall.appcore.utils.logWarn
import com.universall.auth_api.domain.schemas.RegisterSchema
import com.universall.auth_api.domain.usecases.RegisterUseCase
import com.universall.navigation_impl.destinations.auth.AuthDestination
import com.universall.navigation_impl.destinations.auth.LoginDestination
import com.universall.navigation_impl.destinations.main.MainScreenDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
internal class RegisterScreenViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(RegisterScreenUIState.empty())
    val uiState = _uiState.asStateFlow()

    private val _effects = MutableSharedFlow<RegisterScreenUIEffect>()
    val effects = _effects.asSharedFlow()

    private suspend fun register(schema: RegisterSchema) {
        if (_uiState.value.registerRequestState.isFetching) {
            this.logWarn { "Called register during fetch, ignoring" }
            return
        }

        _uiState.update { it.copy(registerRequestState = it.registerRequestState.toLoading()) }

        _uiState.update { state ->
            state.copy(
                registerRequestState = registerUseCase.invoke(schema).fold(
                    onSuccess = {
                        _effects.emit(RegisterScreenUIEffect.Navigate(MainScreenDestination, popUpTo = AuthDestination, inclusive = true))
                        state.registerRequestState.toSuccess(Unit)
                    },
                    onFailure = { error ->
                        this.logError(error) { "Unexpected error occurred" }
                        state.registerRequestState.toError(UIString.of(error.message ?: "Unknown auth error"))
                    }
                )
            )
        }
    }

    private fun launchSendRegister(login: String, password: String, username: String, firstName: String, lastName: String) {
        viewModelScope.launch {
            register(schema = RegisterSchema(login, password, username, firstName, lastName))
        }
    }

    private fun processValidateIntent(intent: RegisterScreenUIIntent.Validate) {
        when (intent) {
            is RegisterScreenUIIntent.Validate.ValidateLoginField -> _uiState.update { state ->
                state.copy(
                    loginFieldState = state.loginFieldState.validate {
                        errorMessage = validateStringLength(minLength = 3, maxLength = 100) ?: UIString.empty()
                    }
                )
            }

            is RegisterScreenUIIntent.Validate.ValidatePasswordField -> _uiState.update { state ->
                state.copy(
                    passwordFieldState = state.passwordFieldState.validate {
                        errorMessage = validateStringLength(minLength = 8, maxLength = 100) ?: UIString.empty()
                    }
                )
            }

            is RegisterScreenUIIntent.Validate.ValidateUsernameField -> _uiState.update { state ->
                state.copy(
                    usernameFieldState = state.usernameFieldState.validate {
                        errorMessage = validateStringLength(minLength = 3, maxLength = 100) ?: UIString.empty()
                    }
                )
            }

            is RegisterScreenUIIntent.Validate.ValidateFirstNameField -> _uiState.update { state ->
                state.copy(
                    firstNameFieldState = state.firstNameFieldState.validate {
                        errorMessage = validateStringLength(minLength = 3, maxLength = 100) ?: UIString.empty()
                    }
                )
            }

            is RegisterScreenUIIntent.Validate.ValidateLastNameField -> _uiState.update { state ->
                state.copy(
                    lastNameFieldState = state.lastNameFieldState.validate {
                        errorMessage = validateStringLength(minLength = 3, maxLength = 100) ?: UIString.empty()
                    }
                )
            }
        }
    }

    private fun processInputIntent(intent: RegisterScreenUIIntent.Input) {
        when (intent) {
            is RegisterScreenUIIntent.Input.InputLogin -> _uiState.update { it.copy(loginFieldState = it.loginFieldState.setNewValue(intent.value)) }
            is RegisterScreenUIIntent.Input.InputPassword -> _uiState.update { it.copy(passwordFieldState = it.passwordFieldState.setNewValue(intent.value)) }
            is RegisterScreenUIIntent.Input.InputUsername -> _uiState.update { it.copy(usernameFieldState = it.usernameFieldState.setNewValue(intent.value)) }
            is RegisterScreenUIIntent.Input.InputFirstName -> _uiState.update { it.copy(firstNameFieldState = it.firstNameFieldState.setNewValue(intent.value)) }
            is RegisterScreenUIIntent.Input.InputLastName -> _uiState.update { it.copy(lastNameFieldState = it.lastNameFieldState.setNewValue(intent.value)) }
        }
    }

    fun onIntent(intent: RegisterScreenUIIntent) {
        when (intent) {
            is RegisterScreenUIIntent.NavigateToLogin -> viewModelScope.launch { _effects.emit(RegisterScreenUIEffect.Navigate(LoginDestination, popUpTo = AuthDestination)) }

            is RegisterScreenUIIntent.Validate -> processValidateIntent(intent)
            is RegisterScreenUIIntent.Input -> processInputIntent(intent)

            is RegisterScreenUIIntent.SendRegister -> launchSendRegister(
                login = uiState.value.loginFieldState.value,
                password = uiState.value.passwordFieldState.value,
                username = uiState.value.usernameFieldState.value,
                firstName = uiState.value.firstNameFieldState.value,
                lastName = uiState.value.lastNameFieldState.value
            )
        }
    }
}
