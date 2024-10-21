package com.example.newsapp.data.repository

import com.example.newsapp.data.mappers.asArticleModel
import com.example.newsapp.data.model.ServerInfoModel
import com.example.newsapp.data.source.api.NewsApiService
import javax.inject.Inject

class NewsFromApiRepository @Inject constructor(
    private val newsApiService: NewsApiService
){

    suspend fun getNewsResponseByQuery(query: String) : ServerInfoModel {
        val serverResponse = newsApiService.getNewsByQuery(query)
        val model = ServerInfoModel(
            status = serverResponse.status,
            totalResults = serverResponse.totalResults,
            articles = serverResponse.articles.map { it.asArticleModel() }
        )
        return model
    }

}