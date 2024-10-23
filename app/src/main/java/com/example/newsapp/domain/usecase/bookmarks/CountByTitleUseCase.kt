package com.example.newsapp.domain.usecase.bookmarks

import com.example.newsapp.domain.repository.BookmarksRepository
import javax.inject.Inject

class CountByTitleUseCase @Inject constructor(
    private val bookmarksRepository: BookmarksRepository
) {

    suspend fun invoke(title: String): Int{
        return bookmarksRepository.countByTitle(title)
    }

}