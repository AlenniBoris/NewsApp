package com.example.newsapp.data.source.api.model

import java.io.Serializable

data class ServerInfoResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleResponse>
) : Serializable