package com.example.newsapp.data.source.api.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ServerInfoResponse(
    @SerializedName("status") val status: String,
    @SerializedName("totalResults") val totalResults: Int,
    @SerializedName("articles") val articles: List<ArticleResponse>
) : Serializable