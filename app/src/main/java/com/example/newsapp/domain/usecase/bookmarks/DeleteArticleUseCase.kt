package com.example.newsapp.domain.usecase.bookmarks

import com.example.newsapp.domain.model.ArticleModel
import com.example.newsapp.domain.repository.BookmarksRepository
import javax.inject.Inject

class DeleteArticleUseCase @Inject constructor(
    private val articleRepository: BookmarksRepository
) {

    suspend fun invoke(article: ArticleModel){
        articleRepository.deleteArticle(article)
    }

}