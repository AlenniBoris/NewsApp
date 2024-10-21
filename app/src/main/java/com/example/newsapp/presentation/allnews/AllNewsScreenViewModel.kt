package com.example.newsapp.presentation.allnews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.repository.NewsFromApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllNewsScreenViewModel @Inject constructor(
     private val newsFromApiRepository: NewsFromApiRepository
) : ViewModel() {

    val screenState = MutableStateFlow(AllNewsScreenState())

    init {
        viewModelScope.launch {
            getNewsResponseByQueryInternal("Car")
        }
    }

    fun getNewsByQuery(query: String){
        viewModelScope.launch {
            screenState.update { state ->
                state.copy(
                    query = query
                )
            }

            getNewsResponseByQueryInternal(query)
        }
    }

    private suspend fun getNewsResponseByQueryInternal(query: String){
        val newsResponse = newsFromApiRepository.getNewsResponseByQuery(query)
        screenState.update { state ->
            state.copy(
                status = newsResponse.status,
                totalResults = newsResponse.totalResults,
                articles = newsResponse.articles
            )
        }
    }

}