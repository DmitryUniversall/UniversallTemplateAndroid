package com.universall.appcore.ui.navigation.pager_nav_host

import androidx.compose.foundation.OverscrollEffect
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.TargetedFlingBehavior
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerScope
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberOverscrollEffect
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
internal fun <T : PagerNavRoute> PagerNavHost(
    modifier: Modifier = Modifier,
    state: PagerNavHostState<T>,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    pageSize: PageSize = PageSize.Fill,
    beyondViewportPageCount: Int = PagerDefaults.BeyondViewportPageCount,
    pageSpacing: Dp = 0.dp,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    flingBehavior: TargetedFlingBehavior? = null,
    userScrollEnabled: Boolean = true,
    reverseLayout: Boolean = false,
    key: ((index: Int) -> Any)? = null,
    pageNestedScrollConnection: NestedScrollConnection? = null,
    snapPosition: SnapPosition = SnapPosition.Start,
    overscrollEffect: OverscrollEffect? = rememberOverscrollEffect(),
    content: @Composable PagerScope.(T) -> Unit
) {
    HorizontalPager(
        modifier = modifier,
        contentPadding = contentPadding,
        pageSize = pageSize,
        beyondViewportPageCount = beyondViewportPageCount,
        pageSpacing = pageSpacing,
        verticalAlignment = verticalAlignment,
        flingBehavior = flingBehavior ?: PagerDefaults.flingBehavior(state = state.asPagerState()),
        userScrollEnabled = userScrollEnabled,
        reverseLayout = reverseLayout,
        key = key,
        pageNestedScrollConnection = pageNestedScrollConnection ?: PagerDefaults.pageNestedScrollConnection(state.asPagerState(), Orientation.Horizontal),
        snapPosition = snapPosition,
        overscrollEffect = overscrollEffect,
        state = state.asPagerState()
    ) { page ->
        content(state.getRouteByPage(page))
    }
}

@Composable
fun <T : PagerNavRoute> rememberPagerNavHostState(
    startRoute: T? = null,
    routes: List<T>
): PagerNavHostState<T> {
    val initPage = startRoute?.let {
        routes.indexOf(startRoute).takeIf { it >= 0 } ?: throw IllegalStateException("Route with name '${startRoute.name}' is not declared in this pager nav host")
    } ?: 0

    val pagerState = rememberPagerState(
        initialPage = initPage,
        pageCount = { routes.size }
    )

    return remember {
        PagerNavHostState(
            pagerState = pagerState,
            routes = routes
        )
    }
}

@Composable
fun <T : PagerNavRoute> PagerNavHost(
    modifier: Modifier = Modifier,
    state: PagerNavHostState<T>,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    pageSize: PageSize = PageSize.Fill,
    beyondViewportPageCount: Int = PagerDefaults.BeyondViewportPageCount,
    pageSpacing: Dp = 0.dp,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    flingBehavior: TargetedFlingBehavior? = null,
    userScrollEnabled: Boolean = true,
    reverseLayout: Boolean = false,
    key: ((index: Int) -> Any)? = null,
    pageNestedScrollConnection: NestedScrollConnection? = null,
    snapPosition: SnapPosition = SnapPosition.Start,
    overscrollEffect: OverscrollEffect? = rememberOverscrollEffect(),
    build: PagerNavHostResolver.Builder<T>.() -> Unit
) {
    val resolver = PagerNavHostResolver.Builder(state).apply(build).build()

    PagerNavHost(
        modifier = modifier,
        contentPadding = contentPadding,
        pageSize = pageSize,
        beyondViewportPageCount = beyondViewportPageCount,
        pageSpacing = pageSpacing,
        verticalAlignment = verticalAlignment,
        flingBehavior = flingBehavior,
        userScrollEnabled = userScrollEnabled,
        reverseLayout = reverseLayout,
        key = key,
        pageNestedScrollConnection = pageNestedScrollConnection,
        snapPosition = snapPosition,
        overscrollEffect = overscrollEffect,
        state = state
    ) { route ->
        resolver.DrawContent(route)
    }
}
