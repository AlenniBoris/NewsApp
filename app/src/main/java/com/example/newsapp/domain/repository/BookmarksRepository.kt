package com.example.newsapp.domain.repository

import com.example.newsapp.domain.model.ArticleModel

interface BookmarksRepository {

    suspend fun addArticle(articleModel: ArticleModel)

    suspend fun deleteArticle(articleModel: ArticleModel)

    suspend fun countByTitle(title: String) : Int

    suspend fun getAllArticles(): List<ArticleModel>

}