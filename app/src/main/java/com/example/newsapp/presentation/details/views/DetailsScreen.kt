package com.example.newsapp.presentation.details.views

import android.app.Activity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.newsapp.domain.model.ArticleModel
import com.example.newsapp.presentation.details.DetailsScreenViewModel
import com.example.newsapp.presentation.uikit.views.AppTopBar


@Composable
fun DetailsScreen(
    viewModel: DetailsScreenViewModel = hiltViewModel(),
    navHostController: NavHostController,
    article: ArticleModel?
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()
    val window = (context as Activity).window

    window.statusBarColor = MaterialTheme.colorScheme.background.toArgb()
    viewModel.assignArticle(article)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppTopBar(
                hasButton = true,
                text = screenState.currentArticle?.title.toString(),
                textVisible = true,
                navController = navHostController
            )
        }
    ) {
        DetailsScreenInsides(
            detailsScreenState = screenState,
            paddingValues = it,
            scrollState = scrollState,
            context = context,
            detailsScreenViewModel = viewModel,
            navHostController = navHostController
        )
    }
}