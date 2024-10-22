package com.example.newsapp.presentation.uikit

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.example.newsapp.R
import com.example.newsapp.data.model.ArticleModel

@Composable
fun ArticleImage(
    currentArticle: ArticleModel?
){
    AsyncImage(
        modifier = Modifier
            .fillMaxWidth(),
        model = currentArticle?.urlToImage,
        contentDescription = "Image",
        contentScale = ContentScale.FillWidth,
        placeholder =
        if (isSystemInDarkTheme()) painterResource(id = R.drawable.ic_placeholder_dark)
        else painterResource(id = R.drawable.ic_placeholder_light)
    )
}

