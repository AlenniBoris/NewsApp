package com.example.newsapp.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.model.ArticleModel
import com.example.newsapp.domain.usecase.bookmarks.AddArticleUseCase
import com.example.newsapp.domain.usecase.bookmarks.CountByTitleUseCase
import com.example.newsapp.domain.usecase.bookmarks.DeleteArticleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val addArticleUseCase: AddArticleUseCase,
    private val deleteArticleUseCase: DeleteArticleUseCase,
    private val countByTitleUseCase: CountByTitleUseCase
) : ViewModel() {

    val screenState = MutableStateFlow(DetailsScreenState())

    fun assignArticle(article: ArticleModel?) {
        screenState.update { state ->
            state.copy(
                currentArticle = article
            )
        }

        if (article != null) {
            viewModelScope.launch {
                val count = countByTitleUseCase.invoke(article.title)
                changeIsFavourite(count != 0)
            }
        }
    }

    fun actionOnBookmarksButton(article: ArticleModel) {
        viewModelScope.launch {
            if (screenState.value.articleIsInBookmarks) {
                removeFromBookmarks(article)
                changeIsFavourite(false)

            } else {
                addToBookmarks(article)
                changeIsFavourite(true)
            }
        }
    }

    private suspend fun addToBookmarks(article: ArticleModel) {
        viewModelScope.launch {
            addArticleUseCase.invoke(article)
        }

    }

    private suspend fun removeFromBookmarks(article: ArticleModel) {
        viewModelScope.launch {
            deleteArticleUseCase.invoke(article)
        }
    }

    private fun changeIsFavourite(isInBookmarks: Boolean) {
        screenState.update { state -> state.copy(articleIsInBookmarks = isInBookmarks) }
    }

}