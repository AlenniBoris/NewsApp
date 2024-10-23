package com.example.newsapp.presentation.allnews

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.usecase.cache.CacheAddArticlesUseCase
import com.example.newsapp.domain.usecase.cache.CacheGetAllArticlesUseCase
import com.example.newsapp.domain.usecase.newsfromapi.GetNewsByQueryUseCase
import com.example.newsapp.utils.Constants
import com.example.newsapp.utils.ExtraFunction
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllNewsScreenViewModel @Inject constructor(
    private val getNewsByQueryUseCase: GetNewsByQueryUseCase,
    private val cacheGetAllArticlesUseCase: CacheGetAllArticlesUseCase,
    private val cacheAddArticlesUseCase: CacheAddArticlesUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {

    val screenState = MutableStateFlow(AllNewsScreenState())

    init {
        viewModelScope.launch {
            getNewsResponseByQueryInternal(Constants.TABS[0])
        }
    }

    fun getNewsByQuery(query: String) {
        viewModelScope.launch {
            screenState.update { state ->
                state.copy(
                    query = query
                )
            }

            viewModelScope.launch {
                getNewsResponseByQueryInternal(query)
            }

        }
    }

    private suspend fun getNewsResponseByQueryInternal(query: String) {

        val cachedQuery = ExtraFunction.readLastQueryFromFile(context)
        screenState.update { state ->
            state.copy(
                cachedQuery = cachedQuery
            )
        }

        if (!ExtraFunction.checkInternetConnection(context) && query == screenState.value.cachedQuery) {
            val cachedArticles = cacheGetAllArticlesUseCase.invoke()

            screenState.update { state ->
                state.copy(
                    status = "cached",
                    totalResults = cachedArticles.size,
                    articles = cachedArticles,
                )
            }

            ExtraFunction.writeLastQueryToFile(query, context)
        } else {

            val newsResponse = getNewsByQueryUseCase.invoke(query)

            screenState.update { state ->
                state.copy(
                    status = newsResponse.status,
                    totalResults = newsResponse.totalResults,
                    articles = newsResponse.articles
                )
            }

            if (screenState.value.articles.isNotEmpty()) {
                screenState.update { state ->
                    state.copy(
                        cachedQuery = query
                    )
                }
                cacheAddArticlesUseCase.invoke(newsResponse.articles)
                ExtraFunction.writeLastQueryToFile(query, context)
            }

        }
    }

}