package com.example.newsapp.data.model

data class ServerInfoModel(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleModel>
)