package com.example.newsapp.presentation.details

import com.example.newsapp.domain.model.ArticleModel

data class DetailsScreenState(
    val articleIsInBookmarks: Boolean = false,
    val currentArticle: ArticleModel? = null
)