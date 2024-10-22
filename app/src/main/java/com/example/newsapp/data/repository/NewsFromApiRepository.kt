package com.example.newsapp.data.repository

import com.example.newsapp.data.mappers.asArticleModel
import com.example.newsapp.data.model.ServerInfoModel
import com.example.newsapp.data.source.api.NewsApiService
import com.example.newsapp.data.source.api.model.ServerInfoResponse

class NewsFromApiRepository(
    private val newsApiService: NewsApiService
) {

    suspend fun getNewsResponseByQuery(query: String): ServerInfoModel {
        val serverResponse = try {
            newsApiService.getNewsByQuery(query)
        } catch (e: Exception) {
            ServerInfoResponse(
                status = "error",
                totalResults = 0,
                articles = emptyList()
            )
        }
        val model = ServerInfoModel(
            status = serverResponse.status,
            totalResults = serverResponse.totalResults,
            articles = serverResponse.articles.map { it.asArticleModel() }
        )
        return model
    }

}