package com.example.newsapp.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newsapp.data.model.ArticleModel
import com.example.newsapp.data.repository.NewsDatabaseRepository
import com.example.newsapp.navigation.Screen
import com.example.newsapp.presentation.bookmarks.BookmarksScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val newsDatabaseRepository: NewsDatabaseRepository
) : ViewModel(){

    val screenState = MutableStateFlow(DetailsScreenState())

    fun assignArticle(article: ArticleModel?){
        screenState.update { state ->
            state.copy(
                currentArticle = article
            )
        }

        if (article != null){
            viewModelScope.launch {
                val count = newsDatabaseRepository.countByTitle(article.title)
                changeIsFavourite(count != 0)
            }
        }
    }

    fun actionOnBookmarksButton(article: ArticleModel){
        viewModelScope.launch {
            if (screenState.value.articleIsInBookmarks){
                removeFromBookmarks(article)
                changeIsFavourite(false)

            } else {
                addToBookmarks(article)
                changeIsFavourite(true)
            }
        }
    }

    suspend fun addToBookmarks(article: ArticleModel) {
        viewModelScope.launch {
            newsDatabaseRepository.addArticle(article)
        }

    }

    suspend fun removeFromBookmarks(article: ArticleModel) {
        viewModelScope.launch {
            newsDatabaseRepository.deleteArticle(article)
        }
    }

    private fun changeIsFavourite(isInBookmarks: Boolean){
        screenState.update { state -> state.copy(articleIsInBookmarks = isInBookmarks) }
    }

}