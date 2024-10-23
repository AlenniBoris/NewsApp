package com.example.newsapp.domain.usecase.bookmarks

import com.example.newsapp.domain.model.ArticleModel
import com.example.newsapp.domain.model.ArticleSourceModel
import com.example.newsapp.domain.repository.BookmarksRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.BeforeEach

class GetAllBookmarksUseCaseTest {

    private lateinit var addArticleUseCase: AddArticleUseCase
    private lateinit var getAllBookmarksUseCase: GetAllBookmarksUseCase
    private val bookmarksRepository: BookmarksRepository = mockk(relaxed = true)

    @Before
    fun setUp() {
        addArticleUseCase = AddArticleUseCase(bookmarksRepository)
        getAllBookmarksUseCase = GetAllBookmarksUseCase(bookmarksRepository)
    }

    @Test
    fun `should call getAllBookmarks on repository`() = runTest {
        coEvery { bookmarksRepository.getAllArticles() } returns emptyList()

        getAllBookmarksUseCase.invoke()

        coVerify(exactly = 1) { bookmarksRepository.getAllArticles() }
    }

    @Test
    fun `return empty list if nothing in database`() = runTest {
        coEvery { bookmarksRepository.getAllArticles() } returns emptyList()

        val result = getAllBookmarksUseCase.invoke()

        assertEquals(0, result.size)

        coVerify(exactly = 1) { bookmarksRepository.getAllArticles() }
    }

    @Test
    fun `should return list with expected amount of articles`() = runTest {

        val article1 = ArticleModel(
            source = ArticleSourceModel(
                id = null,
                name = "Test AddArticle"
            ),
            author = null,
            title = "1",
            description = null,
            url = null,
            urlToImage = null,
            publishedAt = "",
            content = ""
        )
        val article2 = ArticleModel(
            source = ArticleSourceModel(
                id = null,
                name = "Test AddArticle"
            ),
            author = null,
            title = "2",
            description = null,
            url = null,
            urlToImage = null,
            publishedAt = "",
            content = ""
        )

        addArticleUseCase.invoke(article1)
        addArticleUseCase.invoke(article2)

        coEvery { bookmarksRepository.getAllArticles() } returns listOf(article1, article2)

        val result = getAllBookmarksUseCase.invoke()

        assertEquals(2, result.size)

        coVerify(exactly = 1) { bookmarksRepository.getAllArticles() }
    }
}