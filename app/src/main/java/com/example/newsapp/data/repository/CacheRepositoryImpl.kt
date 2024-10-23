package com.example.newsapp.data.repository

import com.example.newsapp.data.mappers.asArticleEntity
import com.example.newsapp.data.mappers.asArticleModel
import com.example.newsapp.data.source.dao.cache.CacheDatabase
import com.example.newsapp.domain.model.ArticleModel
import com.example.newsapp.domain.repository.CacheRepository
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CacheRepositoryImpl @Inject constructor(
    private val cacheDatabase: CacheDatabase
) : CacheRepository {

    override suspend fun addArticles(articleModels: List<ArticleModel>) {
        runBlocking {
            articleModels.forEach { it ->
                cacheDatabase.dao.addArticle(it.asArticleEntity())
            }

        }
    }

    override suspend fun deleteAllArticles() {
        cacheDatabase.dao.clearTable()
    }

    override suspend fun getAllArticles(): List<ArticleModel> {
        return cacheDatabase.dao.getAllArticles().map { it.asArticleModel() }
    }

}