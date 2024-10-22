package com.example.newsapp.presentation.bookmarks

import com.example.newsapp.data.model.ArticleModel
import com.example.newsapp.navigation.Screen

data class BookmarksScreenState(
    val bookmarkedArticles: List<ArticleModel> = emptyList(),
    val thereIsNoBookmarkedArticles: Boolean = false
)
