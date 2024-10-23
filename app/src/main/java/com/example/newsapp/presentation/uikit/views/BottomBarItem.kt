package com.example.newsapp.presentation.uikit.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.newsapp.navigation.Screen


@Composable
fun BottomBarItem(
    screen: Screen,
    navController: NavController,
    currentDest: NavDestination?
) {
    val itemIsActive = currentDest?.hierarchy?.any { it.route == screen.route } == true
    val icon = if (itemIsActive) screen.activeIcon else screen.notActiveIcon

    val contentColor =
        if (itemIsActive) MaterialTheme.colorScheme.tertiary
        else MaterialTheme.colorScheme.secondary

    Box(
        modifier = Modifier.size(64.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Box(
            modifier = Modifier
                .height(64.dp)
                .clip(RoundedCornerShape(32.dp))
                .clickable {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = icon!!),
                contentDescription = "icon",
                tint = contentColor
            )
        }
        AnimatedVisibility(visible = itemIsActive) {
            Divider(
                modifier = Modifier.size(24.dp, 2.dp),
                color = contentColor
            )
        }
    }

}