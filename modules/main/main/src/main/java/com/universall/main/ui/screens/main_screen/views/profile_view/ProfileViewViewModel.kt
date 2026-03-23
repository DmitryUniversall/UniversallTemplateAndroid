package com.universall.main.ui.screens.main_screen.views.profile_view

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
internal class ProfileViewViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileViewUIState.empty())
    val uiState = _uiState.asStateFlow()

    private val _effects = MutableSharedFlow<ProfileViewUIEffect>()
    val effects = _effects.asSharedFlow()

    fun onIntent(intent: ProfileViewUIIntent) {

    }
}
