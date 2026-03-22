package com.universall.auth_impl.ui.screens.auth_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.universall.appcore.ui.state.toError
import com.universall.appcore.ui.state.toLoading
import com.universall.appcore.ui.state.toSuccess
import com.universall.appcore.utils.UIString
import com.universall.appcore.utils.logError
import com.universall.appcore.utils.logWarn
import com.universall.auth_api.domain.schemas.LoginSchema
import com.universall.auth_api.domain.schemas.RegisterSchema
import com.universall.auth_api.domain.usecases.LoginUseCase
import com.universall.auth_api.domain.usecases.RegisterUseCase
import com.universall.auth_impl.R
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(AuthUIState.empty())
    val uiState = _uiState.asStateFlow()

    private val _effects = MutableSharedFlow<AuthUIEffect>()
    val effects = _effects.asSharedFlow()

    private suspend fun login(schema: LoginSchema) {
        if (_uiState.value.isFetchingAnything) {
            this.logWarn { "Called login during fetch, ignoring" }
            return
        }

        _uiState.update { it.copy(loginRequestState = it.loginRequestState.toLoading()) }

        _uiState.update { state ->
            state.copy(
                loginRequestState = loginUseCase.invoke(schema)
                    .fold(
                        onSuccess = { state.loginRequestState.toSuccess(Unit) },
                        onFailure = { error ->
                            this.logError(error) { "Unexpected error occurred" }
                            state.loginRequestState.toError(UIString.of(error.message ?: "Unknown auth error"), error)
                        }
                    )
            )
        }
    }

    private suspend fun register(schema: RegisterSchema) {
        if (_uiState.value.isFetchingAnything) {
            this.logWarn { "Called register during fetch, ignoring" }
            return
        }

        _uiState.update { it.copy(registerRequestState = it.registerRequestState.toLoading()) }

        _uiState.update { state ->
            state.copy(
                registerRequestState = registerUseCase.invoke(schema).fold(
                    onSuccess = { state.registerRequestState.toSuccess(Unit) },
                    onFailure = { error ->
                        this.logError(error) { "Unexpected error occurred" }
                        state.registerRequestState.toError(UIString.of(error.message ?: "Unknown auth error"), error)
                    }
                )
            )
        }
    }

    private fun validateLoginInput(input: AuthUIState.LoginInputInfo) {
        var hasValidationErrors = false

        _uiState.update { state ->
            state.copy(
                loginInputInfo = AuthUIState.LoginInputInfo(
                    login = input.login.validate {
                        if (value.isEmpty()) {
                            hasValidationErrors = true
                            errorMessage = UIString.resource(R.string.empty_field_error)
                            return@validate
                        }

                        if (value.length < 3) {
                            hasValidationErrors = true
                            errorMessage = UIString.resource(R.string.value_too_short_error)
                            return@validate
                        }
                    },
                    password = input.password.validate {
                        if (value.isEmpty()) {
                            hasValidationErrors = true
                            errorMessage = UIString.resource(R.string.empty_field_error)
                            return@validate
                        }

                        if (value.length < 3) {
                            hasValidationErrors = true
                            errorMessage = UIString.resource(R.string.value_too_short_error)
                            return@validate
                        }
                    },
                    isLoginButtonEnabled = !hasValidationErrors
                )
            )
        }
    }

    private fun validateRegisterInput(input: AuthUIState.RegisterInputInfo) {
        _uiState.update { it.copy(registerInputInfo = input) }
    }

    private fun processLoginValidateIntent(intent: AuthUIIntent.Validate.Login) {
        when (intent) {
            is AuthUIIntent.Validate.Login.LoginField -> _uiState.update { state ->
                state.copy(
                    loginInputInfo = AuthUIState.LoginInputInfo(
                        login = intent.fieldState.validate {
                            if (value.isEmpty()) {
                                errorMessage = UIString.resource(R.string.empty_field_error)
                                return@validate
                            }

                            if (value.length < 3) {
                                errorMessage = UIString.resource(R.string.value_too_short_error)
                                return@validate
                            }
                        }
                    )
                )
            }

            is AuthUIIntent.Validate.Login.PasswordField -> TODO()
        }
    }

    private fun processRegisterValidateIntent(intent: AuthUIIntent.Validate.Register) {

    }

    private fun processValidateIntent(intent: AuthUIIntent.Validate) {

    }

    fun onIntent(intent: AuthUIIntent) {
        when (intent) {
            is AuthUIIntent.SetAuthMode -> _uiState.update { it.copy(authMode = intent.authMode) }
            is AuthUIIntent.InputLogin -> _uiState.update { it.copy(loginInputInfo = intent.input) }
            is AuthUIIntent.InputRegister -> _uiState.update { it.copy(registerInputInfo = intent.input) }
            is AuthUIIntent.Validate -> processValidateIntent(intent)

            is AuthUIIntent.SendLogin -> viewModelScope.launch {
                login(
                    schema = LoginSchema(
                        login = intent.input.login.value,
                        password = intent.input.password.value
                    )
                )
            }

            is AuthUIIntent.SendRegister -> viewModelScope.launch {
                register(
                    schema = RegisterSchema(
                        login = intent.input.login.value,
                        password = intent.input.password.value,
                        firstName = intent.input.firstName.value,
                        lastName = intent.input.lastName.value,
                        username = intent.input.username.value
                    )
                )
            }
        }
    }

    fun pageToAuthMode(page: Int): AuthMode {
        return when (page) {
            1 -> AuthMode.REGISTER
            else -> AuthMode.LOGIN
        }
    }

    fun authModeToPage(authMode: AuthMode): Int {
        return when (authMode) {
            AuthMode.LOGIN -> 0
            AuthMode.REGISTER -> 1
        }
    }
}
