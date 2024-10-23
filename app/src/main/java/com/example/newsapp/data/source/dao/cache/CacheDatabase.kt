package com.example.newsapp.data.source.dao.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsapp.data.source.dao.DatabaseDao
import com.example.newsapp.data.source.dao.model.ArticleEntity

@Database(
    entities = [ArticleEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CacheDatabase : RoomDatabase() {
    abstract val dao: DatabaseDao
}