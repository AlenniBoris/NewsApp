package com.example.newsapp.presentation.details.views

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
import com.example.newsapp.data.model.ArticleModel
import com.example.newsapp.navigation.Screen
import com.example.newsapp.presentation.allnews.AllNewsScreenViewModel


@Composable
fun DetailsScreen(
    viewModel: AllNewsScreenViewModel = hiltViewModel(),
    navHostController: NavHostController,
    article: ArticleModel?
){

    val state by viewModel.screenState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Button(
            modifier = Modifier.fillMaxWidth().height(40.dp),
            onClick = { navHostController.popBackStack() }
        ) { Text("Back") }

        Text(
            text = article?.title ?: ""
        )
    }

}