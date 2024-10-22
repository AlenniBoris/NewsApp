package com.example.newsapp.presentation.bookmarks

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.Constants
import com.example.newsapp.data.model.ArticleModel
import com.example.newsapp.data.repository.NewsDatabaseRepository
import com.example.newsapp.presentation.allnews.AllNewsScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarksViewModel @Inject constructor(
    private val newsDatabaseRepository: NewsDatabaseRepository
) : ViewModel() {

    val screenState = MutableStateFlow(BookmarksScreenState())

    init {
        viewModelScope.launch {
            getAllBookmarksInternal()
            Log.d("INIT---", "BOOKMARKS")
        }
    }

    suspend fun getAllBookmarksInternal(){
        val bookmarkedArticles = newsDatabaseRepository.getAllArticles()
        viewModelScope.launch {
            screenState.update { state ->
                state.copy(
                    bookmarkedArticles = bookmarkedArticles,
                    thereIsNoBookmarkedArticles = bookmarkedArticles.isEmpty()
                )
            }
        }
    }

}