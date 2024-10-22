package com.example.newsapp

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.replace
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.navigation.NavigationGraph
import com.example.newsapp.navigation.Screen
import com.example.newsapp.presentation.allnews.views.AllNewsScreen
import com.google.android.material.bottomnavigation.BottomNavigationView


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NewsAppTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainActivityShowFunction()
                }

            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun MainActivityShowFunction(){

    val navController = rememberNavController()
    val screensForIcons = listOf(
        Screen.AllNewsScreen,
        Screen.BookmarksScreen
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen : String? = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = if (screensForIcons.map { it.route }.contains(currentScreen)) {
            appBottomBar(
                navController = navController,
                navBackStackEntry = navBackStackEntry,
                items = screensForIcons
            )
        } else{
            {}
        }
    ) {
            paddingValues -> NavigationGraph(navHostController = navController, pv = paddingValues)
    }
}

@Composable
fun appBottomBar(
    navController: NavController,
    navBackStackEntry: NavBackStackEntry?,
    items: List<Screen>
) : @Composable () -> Unit = {
    val currentRoute = navBackStackEntry?.destination

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .height(64.dp)
    ) {
        items.forEach{screen ->
            BottomBarItem(screen = screen, navController = navController, currentDest = currentRoute)
        }
    }
}

@Composable
fun BottomBarItem(
    screen: Screen,
    navController: NavController,
    currentDest: NavDestination?
){
    val itemIsActive = currentDest?.hierarchy?.any { it.route == screen.route} == true
    val icon = if (itemIsActive) screen.activeIcon else screen.notActiveIcon

    val contentColor =
        if (itemIsActive) MaterialTheme.colorScheme.tertiary
        else MaterialTheme.colorScheme.secondary

    //64 - height of bar, 24 of icon
    Box(
        modifier = Modifier.size(64.dp),
        contentAlignment = Alignment.TopCenter
    ){
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
                contentDescription ="icon",
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