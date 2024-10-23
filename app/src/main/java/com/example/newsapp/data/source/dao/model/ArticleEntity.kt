package com.example.newsapp.data.source.dao.model

import androidx.room.Embedded
import androidx.room.Entity

@Entity(
    tableName = "bookmarks_news",
    primaryKeys = ["title", "publishedAt"]
)
data class ArticleEntity(
    @Embedded(prefix = "source_")
    val source: ArticleSourceEntity,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String
)