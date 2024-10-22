package com.example.newsapp.presentation.details.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.newsapp.R
import com.example.newsapp.data.model.ArticleModel
import com.example.newsapp.navigation.Route
import com.example.newsapp.presentation.details.DetailsScreenViewModel

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
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }

    }
}