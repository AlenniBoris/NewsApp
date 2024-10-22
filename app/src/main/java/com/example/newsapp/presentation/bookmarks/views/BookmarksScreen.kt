package com.example.newsapp.presentation.bookmarks.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.newsapp.presentation.allnews.AllNewsScreenViewModel
import com.example.newsapp.presentation.allnews.views.ArticleCard
import com.example.newsapp.presentation.bookmarks.BookmarksViewModel
import com.example.newsapp.presentation.details.views.AppTopBar


@Composable
fun BookmarksScreen(
    viewModel: BookmarksViewModel = hiltViewModel(),
    navController: NavHostController
){

    val state by viewModel.screenState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getAllBookmarksInternal()
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        AppTopBar(
            hasButton = false,
            text = "Bookmarks",
            textVisible = true,
            navController = navController
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(state.bookmarkedArticles){ article ->
                ArticleCard(
                    article = article,
                    navController = navController
                )
            }
        }
    }

}