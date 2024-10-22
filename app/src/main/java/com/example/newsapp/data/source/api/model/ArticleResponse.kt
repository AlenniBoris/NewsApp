package com.example.newsapp.data.source.api.model

import java.io.Serializable

data class ArticleResponse(
    val source: ArticleSourceResponse,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String
) : Serializable
