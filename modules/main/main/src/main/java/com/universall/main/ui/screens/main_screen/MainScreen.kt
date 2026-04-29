package com.universall.main.ui.screens.main_screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.universall.appcore.appcore.ui.utils.add
import com.universall.appcore.appcore.ui.pager_nav_host.PagerNavHost
import com.universall.appcore.appcore.ui.pager_nav_host.rememberPagerNavHostState
import com.universall.main.ui.navigation.MainScreenPagerNavRoute
import com.universall.main.ui.screens.main_screen.components.nav.bottom_nav_bar.MainScreenBottomNavBar
import com.universall.main.ui.screens.main_screen.views.home_view.HomeView
import com.universall.main.ui.screens.main_screen.views.profile_view.ProfileView

@Composable
fun MainScreen(
    innerPadding: PaddingValues,
    navController: NavController
) {
    MainScreen(
        viewModel = hiltViewModel(),
        innerPadding = innerPadding,
        navController = navController
    )
}

@Composable
internal fun MainScreen(
    viewModel: MainScreenViewModel,
    innerPadding: PaddingValues,
    navController: NavController
) {
    val uiState by viewModel.uiState.collectAsState()

    val pagerState = rememberPagerNavHostState(
        startRoute = MainScreenPagerNavRoute.HomePageNavRoute,
        routes = MainScreenPagerNavRoute.routes
    )

    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                is MainScreenUIEffect.Navigate -> navController.navigate(effect.destination.route) {
                    effect.popUpTo?.let { popUpTo(it.route) { inclusive = effect.inclusive } }
                    launchSingleTop = effect.singleTop
                }

                is MainScreenUIEffect.PagerNavigate -> pagerState.navigateTo(effect.destination)
            }
        }
    }

    Scaffold(
        bottomBar = { MainScreenBottomNavBar(onIntent = viewModel::onIntent, currentRoute = pagerState.currentRoute, innerPadding = innerPadding) },
        containerColor = Color.Transparent
    ) { interfacePadding ->
        val totalPadding = innerPadding.add(interfacePadding)

        PagerNavHost(
            modifier = Modifier.fillMaxSize(),
            state = pagerState
        ) {
            composable(MainScreenPagerNavRoute.HomePageNavRoute) {
                HomeView(innerPadding = totalPadding, navController = navController)
            }

            composable(MainScreenPagerNavRoute.ProfilePageNavRoute) {
                ProfileView(innerPadding = totalPadding, navController = navController)
            }
        }
    }
}
