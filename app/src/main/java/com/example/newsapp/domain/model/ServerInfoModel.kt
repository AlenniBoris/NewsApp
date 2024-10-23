package com.example.newsapp.domain.model

data class ServerInfoModel(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleModel>
)