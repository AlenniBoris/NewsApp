package com.example.newsapp.data.source.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.data.source.dao.model.ArticleEntity

interface CacheDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addArticle(article: ArticleEntity)

    @Query("SELECT * FROM bookmarks_news")
    suspend fun getAllArticles(): List<ArticleEntity>

}