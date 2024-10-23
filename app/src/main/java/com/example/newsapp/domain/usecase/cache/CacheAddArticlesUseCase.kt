package com.example.newsapp.domain.usecase.cache

import com.example.newsapp.domain.model.ArticleModel
import com.example.newsapp.domain.repository.CacheRepository
import javax.inject.Inject

class CacheAddArticlesUseCase @Inject constructor(
    private val cacheRepository: CacheRepository
) {

    suspend fun invoke(articles: List<ArticleModel>) {
        cacheRepository.deleteAllArticles()
        cacheRepository.addArticles(articles)
    }

}