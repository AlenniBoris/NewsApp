package com.example.newsapp.data.source.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.data.source.dao.model.ArticleEntity

@Dao
interface ArticleDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addArticle(articleEntity: ArticleEntity)

    @Delete
    suspend fun deleteArticle(articleEntity: ArticleEntity)

    @Query("SELECT * FROM news_bookmarks")
    suspend fun getAllArticles(): List<ArticleEntity>

    @Query("SELECT COUNT(*) FROM news_bookmarks WHERE title=:title")
    suspend fun countByTitle(title: String): Int

}