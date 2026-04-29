package com.universall.init_impl.ui.screens.init_screen

import com.universall.appcore.appcore.ui.state.RequestState

internal data class InitScreenUIState(
    val restoreAuthRequestState: RequestState<Unit>
) {
    companion object {
        fun empty(): InitScreenUIState = InitScreenUIState(
            restoreAuthRequestState = RequestState.Idle
        )
    }
}
