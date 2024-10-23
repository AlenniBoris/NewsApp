package com.example.newsapp.domain.repository

import com.example.newsapp.domain.model.ArticleModel

interface CacheRepository {

    suspend fun addArticles(articleModels: List<ArticleModel>)

    suspend fun deleteAllArticles()

    suspend fun getAllArticles(): List<ArticleModel>

}