package com.universall.main.ui.screens.main_screen.views.profile_view

internal data class ProfileViewUIState(
    val forTemplate: Int = 0
) {
    companion object {
        fun empty(): ProfileViewUIState = ProfileViewUIState()
    }
}
