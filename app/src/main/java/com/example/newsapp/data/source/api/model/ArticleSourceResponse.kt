package com.example.newsapp.data.source.api.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ArticleSourceResponse(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String
): Serializable
