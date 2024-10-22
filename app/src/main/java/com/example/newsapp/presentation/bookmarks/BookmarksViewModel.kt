package com.example.newsapp.presentation.bookmarks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.repository.NewsDatabaseRepository
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