package com.example.newsapp.data.repository

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.data.mappers.asArticleEntity
import com.example.newsapp.data.mappers.asArticleModel
import com.example.newsapp.data.model.ArticleModel
import com.example.newsapp.data.source.dao.ArticleDatabase
import com.example.newsapp.data.source.dao.model.ArticleEntity
import javax.inject.Inject

class NewsDatabaseRepository(
    private val newsDatabase: ArticleDatabase
){
    suspend fun addArticle(articleModel: ArticleModel){
        newsDatabase.dao.addArticle(articleModel.asArticleEntity())
    }

    suspend fun deleteArticle(articleModel: ArticleModel){
        newsDatabase.dao.deleteArticle(articleModel.asArticleEntity())
    }

    suspend fun getAllArticles(): List<ArticleModel>{
        val articles = try {
            newsDatabase.dao.getAllArticles().map { it.asArticleModel() }
        } catch (e: Exception) { emptyList() }
        return articles
    }

    suspend fun countByTitle(title: String): Int{
        val countResult = try {
            newsDatabase.dao.countByTitle(title)
        } catch (e: Exception) { 0 }
        return countResult
    }
}