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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.newsapp.presentation.allnews.AllNewsScreenViewModel


@Composable
fun BookmarksScreen(
    viewModel: AllNewsScreenViewModel = hiltViewModel(),
    navController: NavHostController
){

    val state by viewModel.screenState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Button(
            modifier = Modifier.fillMaxWidth().height(40.dp),
            onClick = { viewModel.getNewsByQuery("Apple") }
        ) { Text("Apple") }

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(state.articles){ article ->
                Box(
                    modifier = Modifier.background(Color.Black).padding(top = 40.dp)
                ){
                    Column {
                        Text(
                            text = "title = ${article.title}",
                            color = Color.White,
                            fontSize = 14.sp
                        )
                        Text(
                            text = "Author = ${article.author}",
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    }

                }
            }
        }
    }

}