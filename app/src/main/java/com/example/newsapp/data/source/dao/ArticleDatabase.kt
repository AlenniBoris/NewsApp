package com.example.newsapp.data.source.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsapp.data.source.dao.model.ArticleEntity

@Database(
    entities = [ArticleEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ArticleDatabase: RoomDatabase(){
    abstract val dao: ArticleDatabaseDao
}