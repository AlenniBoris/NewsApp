package com.example.newsapp.data.model

import com.example.newsapp.data.source.api.model.ArticleResponse
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ServerInfoModel(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleModel>
)