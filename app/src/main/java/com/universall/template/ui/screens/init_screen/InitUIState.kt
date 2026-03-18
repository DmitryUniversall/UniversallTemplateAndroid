package com.universall.template.ui.screens.init_screen

import com.universall.appcore.ui.state.ResourceState

data class InitUIState(
    val restoreAuthRequestState: ResourceState<Unit>
) {
    companion object {
        fun empty(): InitUIState = InitUIState(
            restoreAuthRequestState = ResourceState.Idle
        )
    }
}
