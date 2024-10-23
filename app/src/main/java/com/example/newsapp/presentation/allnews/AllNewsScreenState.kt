package com.example.newsapp.presentation.allnews

import com.example.newsapp.domain.model.ArticleModel

data class AllNewsScreenState(
    val status: String = "",
    val totalResults: Int = 0,
    val articles: List<ArticleModel> = emptyList(),
    val query: String = "",
    val cachedQuery: String = ""
)
