package com.example.newsapp.presentation.allnews.views

import android.content.res.Resources.Theme
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.internal.composableLambdaNInstance
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.newsapp.Constants
import com.example.newsapp.data.model.ArticleModel
import com.example.newsapp.navigation.Screen
import com.example.newsapp.presentation.allnews.AllNewsScreenViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@Composable
fun AllNewsScreen(
    viewModel: AllNewsScreenViewModel = hiltViewModel(),
    navController: NavHostController
){

    val state by viewModel.screenState.collectAsStateWithLifecycle()


    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        TabScreen(viewModel = viewModel)

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(state.articles){ article ->
                ArticleCard(
                    article = article,
                    navController = navController
                )
            }
        }
    }

}


@Composable
fun TabScreen(
    viewModel: AllNewsScreenViewModel
) {
    var tabIndex by remember { mutableStateOf(0) }


    ScrollableTabRow(selectedTabIndex = tabIndex) {
        Constants.TABS.forEachIndexed { index, title ->
            Tab(
                text = { Text(title) },
                selected = tabIndex == index,
                onClick = {
                    viewModel.getNewsByQuery(title)
                    tabIndex = index
                          },
            )
        }
    }
}

@Composable
fun ArticleCard(
    article: ArticleModel,
    navController: NavHostController
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {

//                val gson: Gson = GsonBuilder().create()
//                val articleJson = gson.toJson(article, ArticleModel::class.java)
//
//                val encoded = URLEncoder.encode(articleJson, StandardCharsets.UTF_8.toString())
//
//                navController.currentBackStackEntry?.arguments?.putSerializable("article", article)

                val articleJson = URLEncoder.encode(Gson().toJson(article), "UTF-8")

                navController.navigate(
                    Screen.DetailsScreen.route + articleJson
                )
                Log.d("article---", article.toString())
            }
            .background(MaterialTheme.colorScheme.onBackground.copy(0.4f))
            .wrapContentWidth()
    ) {

        Text(
            text = article.title,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
        )

        Text(
            text = article.description ?: "",
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 15.dp)
        )

    }
}

