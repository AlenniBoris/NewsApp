package com.example.newsapp.di

import com.example.newsapp.data.repository.BookmarksRepositoryImpl
import com.example.newsapp.data.repository.NewsFromApiRepositoryImpl
import com.example.newsapp.domain.repository.BookmarksRepository
import com.example.newsapp.domain.repository.NewsFromApiRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindDatabaseRepository(newsDatabaseRepositoryImpl: BookmarksRepositoryImpl): BookmarksRepository

    @Binds
    fun bindNewsFromApiRepository(newsFromApiRepositoryImpl: NewsFromApiRepositoryImpl): NewsFromApiRepository

}