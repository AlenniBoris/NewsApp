package com.example.newsapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.navigation.NavigationGraph
import com.example.newsapp.navigation.Screen
import com.example.newsapp.presentation.uikit.views.appBottomBar
import com.example.newsapp.presentation.uikit.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint


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
fun MainActivityShowFunction() {

    val navController = rememberNavController()
    val screensForIcons = listOf(
        Screen.AllNewsScreen,
        Screen.BookmarksScreen
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen: String? = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = if (screensForIcons.map { it.route }.contains(currentScreen)) {
            appBottomBar(
                navController = navController,
                navBackStackEntry = navBackStackEntry,
                items = screensForIcons
            )
        } else {
            {}
        }
    ) { paddingValues ->
        NavigationGraph(navHostController = navController, pv = paddingValues)
    }
}
