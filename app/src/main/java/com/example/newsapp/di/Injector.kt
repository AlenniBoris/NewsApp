package com.example.newsapp.di

import com.example.newsapp.BuildConfig
import android.app.Application
import androidx.room.Room
import com.example.newsapp.data.source.api.NewsApiService
import com.example.newsapp.data.source.dao.bookmarks.BookmarksDatabase
import com.example.newsapp.data.source.dao.cache.CacheDatabase
import com.example.newsapp.utils.Constants
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
    private const val BOOKMARKS_DATABASE_FILE = "database-data.db"
    private const val CACHE_DATABASE_FILE = "database-cache.db"

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
                .addHeader(HEADER_AUTHORIZATION, BuildConfig.API_KEY)
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
    fun provideBookmarksDatabase(application: Application): BookmarksDatabase =
        Room.databaseBuilder(
            application,
            BookmarksDatabase::class.java,
            BOOKMARKS_DATABASE_FILE
        ).build()

    @Singleton
    @Provides
    fun provideCacheDatabase(application: Application): CacheDatabase =
        Room.databaseBuilder(
            application,
            CacheDatabase::class.java,
            CACHE_DATABASE_FILE
        ).build()

}