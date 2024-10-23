package com.example.newsapp.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.newsapp.domain.model.ArticleModel

class SharedViewModel : ViewModel() {

    var selectedArticle = mutableStateOf<ArticleModel?>(null)
        private set

    fun selectArticle(article: ArticleModel) {
        selectedArticle.value = article
    }
}