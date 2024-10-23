package com.example.newsapp.presentation.uikit.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.example.newsapp.navigation.Screen

@Composable
fun appBottomBar(
    navController: NavController,
    navBackStackEntry: NavBackStackEntry?,
    items: List<Screen>
): @Composable () -> Unit = {
    val currentRoute = navBackStackEntry?.destination

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .height(64.dp)
    ) {
        items.forEach { screen ->
            BottomBarItem(
                screen = screen,
                navController = navController,
                currentDest = currentRoute
            )
        }
    }
}