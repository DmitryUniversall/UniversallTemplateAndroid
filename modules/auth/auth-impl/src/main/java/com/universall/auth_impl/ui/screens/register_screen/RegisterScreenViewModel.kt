package com.universall.auth_impl.ui.screens.register_screen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
internal class RegisterScreenViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(RegisterScreenUIState.empty())
    val uiState = _uiState.asStateFlow()

    private val _effects = MutableSharedFlow<RegisterScreenUIEffect>()
    val effects = _effects.asSharedFlow()
}
