package com.example.newsapp.presentation.bookmarks.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.newsapp.R
import com.example.newsapp.domain.SharedViewModel
import com.example.newsapp.presentation.bookmarks.BookmarksViewModel
import com.example.newsapp.presentation.uikit.AppTopBar
import com.example.newsapp.presentation.uikit.ArticleCard


@Composable
fun BookmarksScreen(
    viewModel: BookmarksViewModel = hiltViewModel(),
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {

    val state by viewModel.screenState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getAllBookmarksInternal()
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        AppTopBar(
            hasButton = false,
            text = stringResource(R.string.bookmarks),
            textVisible = true,
            navController = navController
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(state.bookmarkedArticles) { article ->
                ArticleCard(
                    article = article,
                    navController = navController,
                    sharedViewModel = sharedViewModel
                )
            }
        }
    }

}