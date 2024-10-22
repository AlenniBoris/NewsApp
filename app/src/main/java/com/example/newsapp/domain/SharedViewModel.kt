package com.example.newsapp.domain

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.newsapp.data.model.ArticleModel

class SharedViewModel : ViewModel() {
    // Состояние для хранения выбранной статьи
    var selectedArticle = mutableStateOf<ArticleModel?>(null)
        private set

    // Функция для установки выбранной статьи
    fun selectArticle(article: ArticleModel) {
        selectedArticle.value = article
    }
}