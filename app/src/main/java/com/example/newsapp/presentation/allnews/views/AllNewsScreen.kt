package com.example.newsapp.presentation.allnews.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.newsapp.domain.ExtraFunction
import com.example.newsapp.domain.SharedViewModel
import com.example.newsapp.presentation.uikit.EmptyScreen
import com.example.newsapp.presentation.allnews.AllNewsScreenViewModel
import com.example.newsapp.presentation.uikit.ArticleCard


@Composable
fun AllNewsScreen(
    viewModel: AllNewsScreenViewModel = hiltViewModel(),
    navController: NavHostController,
    sharedViewModel: SharedViewModel
){

    val state by viewModel.screenState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        AllNewsTabScreen(
            viewModel = viewModel,
            queryTab = state.query,
        )

        if (state.status == "error"){

            if (!ExtraFunction.checkInternetConnection(LocalContext.current)){
                EmptyScreen(
                    onExploreClicked = {
                        viewModel.getNewsByQuery(state.query)
                    },
                    text = "Ooops, something with internet",
                    hasInternet = false,
                    btnText = "Try again"
                )
            }else{
                EmptyScreen(
                    onExploreClicked = {
                        viewModel.getNewsByQuery(state.query)
                    },
                    text = "Ooops, something gone wrong",
                    hasInternet = true,
                    btnText = "Try again"
                )
            }

        }else{

            if (state.articles.isEmpty()){
                if(state.query != ""){
                    EmptyScreen(
                        onExploreClicked = {
                            viewModel.getNewsByQuery(state.query)
                        },
                        text = "Nothing found",
                        hasInternet = true,
                        btnText = "Try again"
                    )
                }else{
                    EmptyScreen(
                        onExploreClicked = {
                            viewModel.getNewsByQuery(state.query)
                        },
                        text = "Nothing found",
                        hasInternet = true,
                        btnText = "Try again",
                        isLoading = true
                    )
                }

            }else{
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(state.articles){ article ->
                        ArticleCard(
                            article = article,
                            navController = navController,
                            sharedViewModel = sharedViewModel
                        )
                    }
                }
            }

        }

    }

}