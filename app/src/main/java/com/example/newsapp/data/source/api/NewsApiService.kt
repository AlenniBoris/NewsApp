package com.example.newsapp.data.source.api

import com.example.newsapp.data.source.api.model.ServerInfoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("everything")
    suspend fun getNewsByQuery(
        @Query("q") query: String
    ) : ServerInfoResponse

}