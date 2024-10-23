package com.example.newsapp.data.source.dao.bookmarks

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsapp.data.source.dao.DatabaseDao
import com.example.newsapp.data.source.dao.model.ArticleEntity

@Database(
    entities = [ArticleEntity::class],
    version = 1,
    exportSchema = false
)
abstract class BookmarksDatabase : RoomDatabase() {
    abstract val dao: DatabaseDao
}