package com.example.newsapp.domain.usecase.newsfromapi

import com.example.newsapp.domain.model.ServerInfoModel
import com.example.newsapp.domain.repository.NewsFromApiRepository
import javax.inject.Inject

class GetNewsByQueryUseCase @Inject constructor(
    private val newsFromApiRepository: NewsFromApiRepository
) {

    suspend fun invoke(query: String): ServerInfoModel{
        return newsFromApiRepository.getNewsResponseByQuery(query)
    }

}