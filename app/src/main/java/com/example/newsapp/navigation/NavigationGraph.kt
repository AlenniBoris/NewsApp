package com.example.newsapp.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.newsapp.domain.SharedViewModel
import com.example.newsapp.presentation.allnews.views.AllNewsScreen
import com.example.newsapp.presentation.bookmarks.views.BookmarksScreen
import com.example.newsapp.presentation.details.views.DetailsScreen

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun NavigationGraph(
    navHostController: NavHostController,
    pv: PaddingValues
){
    val sharedViewModel: SharedViewModel = viewModel()

    NavHost(
        navController = navHostController,
        startDestination = Route.AllNewsRoute.route,
        modifier = Modifier.padding(pv)
    ){

        composable(Route.AllNewsRoute.route){
            AllNewsScreen(
                navController = navHostController,
                sharedViewModel = sharedViewModel
            )
        }

        composable(Route.BookmarksRoute.route){
            BookmarksScreen(
                navController = navHostController,
                sharedViewModel = sharedViewModel
            )
        }

        composable(Route.DetailsRoute.route){ backStackEntry ->
//            val gson: Gson = GsonBuilder().create()
//            val articleJson = backStackEntry.arguments?.getString("article")
//            val decoded = URLDecoder.decode(articleJson)
//            val articleObject = gson.fromJson(articleJson, ArticleModel::class.java)

//            val articleObject = backStackEntry.arguments?.getSerializable("article") as? ArticleModel

//            val articleJson = backStackEntry.arguments?.getString("article")
//            val articleObject = Gson().fromJson(articleJson, ArticleModel::class.java)

//            Log.d("got article", articleObject.toString())
            DetailsScreen(
                navHostController = navHostController,
                article = sharedViewModel.selectedArticle.value
            )
        }

    }
}