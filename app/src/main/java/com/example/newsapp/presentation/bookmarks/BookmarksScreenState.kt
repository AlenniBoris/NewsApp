package com.example.newsapp.presentation.bookmarks

import com.example.newsapp.domain.model.ArticleModel

data class BookmarksScreenState(
    val bookmarkedArticles: List<ArticleModel> = emptyList(),
    val thereIsNoBookmarkedArticles: Boolean = false
)
