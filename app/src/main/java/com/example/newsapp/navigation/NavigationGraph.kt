package com.example.newsapp.navigation

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.newsapp.data.model.ArticleModel
import com.example.newsapp.presentation.allnews.views.AllNewsScreen
import com.example.newsapp.presentation.bookmarks.views.BookmarksScreen
import com.example.newsapp.presentation.details.views.DetailsScreen
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.net.URLDecoder

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun NavigationGraph(
    navHostController: NavHostController,
    pv: PaddingValues
){
    NavHost(
        navController = navHostController,
        startDestination = Route.AllNewsRoute.route,
        modifier = Modifier.padding(pv)
    ){

        composable(Route.AllNewsRoute.route){
            AllNewsScreen(
                navController = navHostController
            )
        }

        composable(Route.BookmarksRoute.route){
            BookmarksScreen(
                navController = navHostController
            )
        }

        composable(Route.DetailsRoute.route){ backStackEntry ->
//            val gson: Gson = GsonBuilder().create()
//            val articleJson = backStackEntry.arguments?.getString("article")
//            val decoded = URLDecoder.decode(articleJson)
//            val articleObject = gson.fromJson(articleJson, ArticleModel::class.java)

//            val articleObject = backStackEntry.arguments?.getSerializable("article") as? ArticleModel

            val articleJson = backStackEntry.arguments?.getString("article")
            val articleObject = Gson().fromJson(articleJson, ArticleModel::class.java)

            Log.d("got article", articleObject.toString())
            DetailsScreen(
                navHostController = navHostController,
                article = articleObject
            )
        }

    }
}