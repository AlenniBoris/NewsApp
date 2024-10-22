package com.example.newsapp.data.repository

import com.example.newsapp.data.mappers.asArticleEntity
import com.example.newsapp.data.mappers.asArticleModel
import com.example.newsapp.data.model.ArticleModel
import com.example.newsapp.data.source.dao.ArticleDatabase

class NewsDatabaseRepository(
    private val newsDatabase: ArticleDatabase
) {
    suspend fun addArticle(articleModel: ArticleModel) {
        newsDatabase.dao.addArticle(articleModel.asArticleEntity())
    }

    suspend fun deleteArticle(articleModel: ArticleModel) {
        newsDatabase.dao.deleteArticle(articleModel.asArticleEntity())
    }

    suspend fun getAllArticles(): List<ArticleModel> {
        val articles = try {
            newsDatabase.dao.getAllArticles().map { it.asArticleModel() }
        } catch (e: Exception) {
            emptyList()
        }
        return articles
    }

    suspend fun countByTitle(title: String): Int {
        val countResult = try {
            newsDatabase.dao.countByTitle(title)
        } catch (e: Exception) {
            0
        }
        return countResult
    }
}