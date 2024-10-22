package com.example.newsapp.di

import android.app.Application
import androidx.room.Room
import com.example.newsapp.data.repository.NewsDatabaseRepository
import com.example.newsapp.data.repository.NewsFromApiRepository
import com.example.newsapp.data.source.api.NewsApiService
import com.example.newsapp.data.source.dao.ArticleDatabase
import com.example.newsapp.domain.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object Injector {

    private const val HEADER_AUTHORIZATION = "Authorization"
    private const val DATABASE_FILE = "news-database-data.db"

    @Singleton
    @Provides
    fun provideHttpLogger(): HttpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Singleton
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader(HEADER_AUTHORIZATION, Constants.API_KEY)
                .build()

            chain.proceed(request)
        }
        .addNetworkInterceptor(loggingInterceptor)
        .build()

    @Singleton
    @Provides
    fun provideNewsApiService(okHttpClient: OkHttpClient): NewsApiService =
        Retrofit
            .Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(NewsApiService::class.java)

    @Singleton
    @Provides
    fun provideNewsFromApiRepository(newsApiService: NewsApiService): NewsFromApiRepository =
        NewsFromApiRepository(newsApiService)

    @Singleton
    @Provides
    fun provideArticlesDatabase(application: Application): ArticleDatabase =
        Room.databaseBuilder(
            application,
            ArticleDatabase::class.java,
            DATABASE_FILE
        ).build()

    @Singleton
    @Provides
    fun provideNewsDatabaseRepository(database: ArticleDatabase): NewsDatabaseRepository =
        NewsDatabaseRepository(database)

}