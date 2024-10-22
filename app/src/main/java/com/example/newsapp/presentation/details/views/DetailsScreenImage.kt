package com.example.newsapp.presentation.details.views

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.newsapp.R
import com.example.newsapp.data.model.ArticleModel
import com.example.newsapp.navigation.Screen

@Composable
fun DetailsScreenImage(
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