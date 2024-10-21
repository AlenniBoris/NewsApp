package com.example.newsapp.presentation.home

import com.example.newsapp.data.model.ArticleModel
import com.example.newsapp.data.model.ServerInfoModel

data class HomeScreenState(
    val status: String = "",
    val totalResults: Int = 0,
    val articles: List<ArticleModel> = emptyList(),
    val query: String = ""
)
