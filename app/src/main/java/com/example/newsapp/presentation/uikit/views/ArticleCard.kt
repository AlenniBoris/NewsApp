package com.example.newsapp.presentation.uikit.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.newsapp.R
import com.example.newsapp.domain.model.ArticleModel
import com.example.newsapp.presentation.SharedViewModel
import com.example.newsapp.navigation.Screen

@Composable
fun ArticleCard(
    article: ArticleModel,
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                sharedViewModel.selectArticle(article)
                navController.navigate(
                    Screen.DetailsScreen.route
                )
            }
            .background(MaterialTheme.colorScheme.onBackground.copy(0.2f))
    ) {

        ArticleImage(
            currentArticle = article
        )

        Text(
            text = stringResource(R.string.source_text) + article.source.name,
            fontSize = 10.sp,
            textAlign = TextAlign.Center,
        )

        Text(
            text = article.title,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 20.dp)
        )

        Text(
            text = article.description ?: "",
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 15.dp)
        )

    }
}