package com.universall.main.ui.screens.main_screen.components.nav.bottom_nav_bar

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.adamglin.PhosphorIcons
import com.adamglin.phosphoricons.Regular
import com.adamglin.phosphoricons.regular.House
import com.adamglin.phosphoricons.regular.User
import com.universall.appcore.ui.theme.locals.Locals
import com.universall.main.ui.navigation.MainScreenPagerNavRoute
import com.universall.main.ui.screens.main_screen.MainScreenUIIntent

@Composable
private fun BottomNavItem(
    icon: ImageVector,
    selected: Boolean,
    onClick: () -> Unit
) {
    val colors = MaterialTheme.colorScheme

    val backgroundAlpha by animateFloatAsState(
        targetValue = if (selected) 1f else 0f,
        animationSpec = tween(
            durationMillis = 250,
            easing = FastOutSlowInEasing
        ),
        label = "BackgroundAlpha"
    )

    val iconAlpha by animateFloatAsState(
        targetValue = if (selected) 1f else 0.7f,
        animationSpec = tween(
            durationMillis = 250,
            easing = FastOutSlowInEasing
        ),
        label = "IconAlpha"
    )

    Box(
        modifier = Modifier
            .height(70.dp)
            .width(100.dp)
            .padding(8.dp)
            .background(
                color = colors.surfaceVariant.copy(alpha = backgroundAlpha),
                shape = RoundedCornerShape(percent = 50)
            )
            .clip(RoundedCornerShape(percent = 50))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(32.dp),
            imageVector = icon,
            contentDescription = null,
            tint = colors.primary.copy(iconAlpha)
        )
    }
}

@Composable
internal fun MainScreenBottomNavBar(
    onIntent: (MainScreenUIIntent) -> Unit,
    currentRoute: MainScreenPagerNavRoute,
    innerPadding: PaddingValues
) {
    val colors = MaterialTheme.colorScheme
    val spacing = Locals.spacing
    val shapes = Locals.shapes
    val dimens = Locals.dimens

    Surface(
        modifier = Modifier
            .padding(horizontal = spacing.screenPadding)
            .padding(bottom = innerPadding.calculateBottomPadding() + spacing.screenPadding)
            .fillMaxWidth(),
        shape = shapes.roundedXXL,
        color = colors.surface,
        shadowElevation = dimens.xxs
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            BottomNavItem(
                icon = PhosphorIcons.Regular.House,
                selected = currentRoute == MainScreenPagerNavRoute.HomePageNavRoute,
                onClick = { onIntent(MainScreenUIIntent.NavigateToHome) }
            )

            BottomNavItem(
                icon = PhosphorIcons.Regular.User,
                selected = currentRoute == MainScreenPagerNavRoute.ProfilePageNavRoute,
                onClick = { onIntent(MainScreenUIIntent.NavigateToProfile) }
            )
        }
    }
}
