package com.example.newsapp.presentation.details.views

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Download
import androidx.compose.material3.Button
import androidx.compose.material.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.newsapp.R
import com.example.newsapp.data.model.ArticleModel
import com.example.newsapp.navigation.Route
import com.example.newsapp.navigation.Screen
import com.example.newsapp.presentation.allnews.AllNewsScreenViewModel
import com.example.newsapp.presentation.details.DetailsScreenViewModel


@Composable
fun DetailsScreen(
    viewModel: DetailsScreenViewModel = hiltViewModel(),
    navHostController: NavHostController,
    article: ArticleModel?
){


    val context = LocalContext.current

    val screenState by viewModel.screenState.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    viewModel.assignArticle(article)

    val window = (context as Activity).window
    window.statusBarColor = MaterialTheme.colorScheme.background.toArgb()

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
        val pd = it
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(pd)
                .padding(horizontal = 24.dp)
                .verticalScroll(scrollState)
        ) {
            DetailsImage(currentArticle = screenState.currentArticle)
            Divider(modifier = Modifier.height(15.dp))
            Text(
                text = "Author: ${screenState.currentArticle?.author.toString()}",
                fontSize = 20.sp
            )
            Text(
                text = "Source: ${screenState.currentArticle?.source?.name.toString()}",
                fontSize = 20.sp
            )
            Text(
                text = "url: ${screenState.currentArticle?.url.toString()}",
                fontSize = 20.sp
            )
            Text(
                text = "Published at: ${screenState.currentArticle?.publishedAt.toString()}",
                fontSize = 20.sp
            )
            Text(
                modifier = Modifier.padding(vertical = 20.dp),
                text = screenState.currentArticle?.content.toString(),
                fontSize = 20.sp
            )
            if (screenState.currentArticle != null) {
                DetailsBottomBarButtons(
                    detailsScreenViewModel = viewModel,
                    currentArticle = screenState.currentArticle!!,
                    currentArticleIsInDatabase = screenState.articleIsInBookmarks,
                    navController = navHostController
                )
            }
        }
    }

}


@Composable
fun DetailsImage(
    currentArticle: ArticleModel?
){
    AsyncImage(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp)
            .clip(RoundedCornerShape(24.dp)),
        model = currentArticle?.urlToImage,
        contentDescription = "Image",
        contentScale = ContentScale.FillWidth,
        placeholder =
        if (isSystemInDarkTheme()) painterResource(id = R.drawable.ic_placeholder_dark)
        else painterResource(id = R.drawable.ic_placeholder_light)
    )
}

@Composable
fun AppTopBar(
    hasButton: Boolean,
    text: String,
    textVisible: Boolean,
    navController: NavHostController
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 17.dp)
            .padding(horizontal = if (text == "Bookmarks") 0.dp else 24.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        if (hasButton){
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .size(40.dp)
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Btn back",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }

        if (textVisible){
            Text(
                text = text,
                fontSize = 22.sp,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun DetailsBottomBarButtons(
    detailsScreenViewModel: DetailsScreenViewModel,
    currentArticle: ArticleModel,
    currentArticleIsInDatabase: Boolean,
    navController: NavHostController
){

    Row(
        modifier = Modifier
            .padding(vertical = 24.dp)
            .background(MaterialTheme.colorScheme.background)
            .fillMaxWidth()
            .height(48.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(24.dp))
                .size(48.dp)
                .background(MaterialTheme.colorScheme.primary)
                .clickable {
                    val prevRoute = navController.previousBackStackEntry?.destination?.route

                    when (prevRoute) {
                        Route.AllNewsRoute.route -> {
                            detailsScreenViewModel.actionOnBookmarksButton(currentArticle)
                        }

                        Route.BookmarksRoute.route -> {
                            detailsScreenViewModel.actionOnBookmarksButton(currentArticle)
                            navController.popBackStack()
                        }
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(
                    id =
                    if(currentArticleIsInDatabase) R.drawable.icon_bookmarks_active
                    else R.drawable.icon_bookmarks),
                contentDescription = "Btn bookmarks",
                tint = MaterialTheme.colorScheme.onBackground
            )
        }

    }
}