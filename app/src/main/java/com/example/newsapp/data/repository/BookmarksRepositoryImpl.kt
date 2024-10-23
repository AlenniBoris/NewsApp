package com.example.newsapp.data.repository

import com.example.newsapp.data.mappers.asArticleEntity
import com.example.newsapp.data.mappers.asArticleModel
import com.example.newsapp.domain.model.ArticleModel
import com.example.newsapp.data.source.dao.ArticleDatabase
import com.example.newsapp.domain.repository.BookmarksRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookmarksRepositoryImpl @Inject constructor(
    private val newsDatabase: ArticleDatabase
) : BookmarksRepository {

    override suspend fun addArticle(articleModel: ArticleModel) {
        newsDatabase.dao.addArticle(articleModel.asArticleEntity())
    }

    override suspend fun deleteArticle(articleModel: ArticleModel) {
        newsDatabase.dao.deleteArticle(articleModel.asArticleEntity())
    }

    override suspend fun getAllArticles(): List<ArticleModel> {
        val articles = try {
            newsDatabase.dao.getAllArticles().map { it.asArticleModel() }
        } catch (e: Exception) {
            emptyList()
        }
        return articles
    }

    override suspend fun countByTitle(title: String): Int {
        val countResult = try {
            newsDatabase.dao.countByTitle(title)
        } catch (e: Exception) {
            0
        }
        return countResult
    }
}