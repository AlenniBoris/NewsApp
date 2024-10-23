package com.example.newsapp.domain.repository

import com.example.newsapp.domain.model.ServerInfoModel

interface NewsFromApiRepository {

    suspend fun getNewsResponseByQuery(query: String): ServerInfoModel

}