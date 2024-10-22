package com.example.newsapp.data.source.dao.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsapp.data.model.ArticleSourceModel

@Entity("news_bookmarks")
data class ArticleEntity(
    @Embedded(prefix = "source_")
    val source: ArticleSourceEntity,
    val author: String?,
    @PrimaryKey val title: String,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String
)