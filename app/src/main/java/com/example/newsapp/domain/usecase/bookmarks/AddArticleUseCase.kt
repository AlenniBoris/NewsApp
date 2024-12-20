package com.example.newsapp.domain.usecase.bookmarks

import com.example.newsapp.domain.model.ArticleModel
import com.example.newsapp.domain.repository.BookmarksRepository
import javax.inject.Inject

class AddArticleUseCase @Inject constructor(
    private val bookmarksRepository: BookmarksRepository
) {

    suspend fun invoke(article: ArticleModel) {
        bookmarksRepository.addArticle(article)
    }

}