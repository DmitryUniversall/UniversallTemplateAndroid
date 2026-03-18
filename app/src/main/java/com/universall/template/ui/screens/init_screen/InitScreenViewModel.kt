package com.universall.template.ui.screens.init_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.universall.appcore.ui.state.isFetching
import com.universall.appcore.ui.state.toError
import com.universall.appcore.ui.state.toLoading
import com.universall.appcore.ui.state.toRefreshing
import com.universall.appcore.ui.state.toSuccess
import com.universall.appcore.utils.logWarn
import com.universall.auth_api.domain.usecases.LocalLogoutUseCase
import com.universall.auth_api.domain.usecases.RestoreAuthStateUseCase
import com.universall.auth_impl.ui.navigation.AuthDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InitScreenViewModel @Inject constructor(
    private val restoreAuthStateUseCase: RestoreAuthStateUseCase,
    private val localLogoutUseCase: LocalLogoutUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(InitUIState.empty())
    val uiState = _uiState.asStateFlow()

    private val _effects = MutableSharedFlow<InitUIEffect>()
    val effects = _effects.asSharedFlow()

    suspend fun restoreAuthState(refresh: Boolean = false) {
        if (_uiState.value.restoreAuthRequestState.isFetching) {
            this.logWarn { "Called restoreAuthState during fetch, ignoring" }
        }

        _uiState.update { it.copy(restoreAuthRequestState = if (refresh) it.restoreAuthRequestState.toRefreshing() else it.restoreAuthRequestState.toLoading()) }

        _uiState.update { state ->
            state.copy(
                restoreAuthRequestState = restoreAuthStateUseCase.invoke().fold(
                    onSuccess = {
                        state.restoreAuthRequestState.toSuccess(Unit).also {
                            _effects.emit(InitUIEffect.Navigate(AuthDestination))
                        }
                    },
                    onFailure = { error ->
                        state.restoreAuthRequestState.toError(error.message ?: "Unknown error during auth restore", error)
                    }
                )
            )
        }
    }

    suspend fun localLogout() {
        localLogoutUseCase.invoke()
    }

    fun onIntent(intent: InitIntent) {
        when (intent) {
            InitIntent.RestoreAuthState -> viewModelScope.launch { restoreAuthState() }
            InitIntent.LocalLogout -> viewModelScope.launch { localLogout() }
            InitIntent.Refresh -> viewModelScope.launch { restoreAuthState(refresh = true) }
        }
    }
}
