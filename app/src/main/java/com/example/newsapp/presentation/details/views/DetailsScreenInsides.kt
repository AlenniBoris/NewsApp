package com.example.newsapp.presentation.details.views

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.newsapp.R
import com.example.newsapp.presentation.details.DetailsScreenState
import com.example.newsapp.presentation.details.DetailsScreenViewModel

@Composable
fun DetailsScreenInsides(
    detailsScreenState: DetailsScreenState,
    paddingValues: PaddingValues,
    scrollState: ScrollState,
    context: Context,
    detailsScreenViewModel: DetailsScreenViewModel,
    navHostController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 24.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        DetailsScreenImage(
            currentArticle = detailsScreenState.currentArticle
        )
        Text(
            text = "Author: ${detailsScreenState.currentArticle?.author.toString()}",
            fontSize = 20.sp
        )
        Text(
            text = "Source: ${detailsScreenState.currentArticle?.source?.name.toString()}",
            fontSize = 20.sp,
        )
        Text(
            text = "url: ${detailsScreenState.currentArticle?.url.toString()}",
            fontSize = 20.sp,
            modifier = Modifier.clickable {
                val searchQuery =
                    detailsScreenState.currentArticle?.url.toString().substringAfter("://")
                        .replace("/", " ")
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.google.com/search?q=$searchQuery")
                )
                context.startActivity(intent)
            }
        )
        Text(
            text = stringResource(R.string.published_text)
                    + detailsScreenState.currentArticle?.publishedAt.toString(),
            fontSize = 20.sp
        )
        Text(
            modifier = Modifier.padding(vertical = 20.dp),
            text = detailsScreenState.currentArticle?.content.toString(),
            fontSize = 20.sp
        )
        if (detailsScreenState.currentArticle != null) {
            DetailsBottomBarButtons(
                detailsScreenViewModel = detailsScreenViewModel,
                currentArticle = detailsScreenState.currentArticle,
                currentArticleIsInDatabase = detailsScreenState.articleIsInBookmarks,
                navController = navHostController
            )
        }
    }
}