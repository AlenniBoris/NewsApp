package com.example.newsapp.domain.usecase.bookmarks

import com.example.newsapp.domain.model.ArticleModel
import com.example.newsapp.domain.model.ArticleSourceModel
import com.example.newsapp.domain.repository.BookmarksRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class AddArticleUseCaseTest {

    private lateinit var addArticleUseCase: AddArticleUseCase
    private lateinit var countByTitleUseCase: CountByTitleUseCase
    private val bookmarksRepository: BookmarksRepository = mockk(relaxed = true)

    @Before
    fun setUp() {
        addArticleUseCase = AddArticleUseCase(bookmarksRepository)
        countByTitleUseCase = CountByTitleUseCase(bookmarksRepository)
    }

    @Test
    fun `should call addArticle on repository`() = runTest {

        val article = ArticleModel(
            source = ArticleSourceModel(
                id = null,
                name = "Test AddArticle"
            ),
            author = null,
            title = "test title",
            description = null,
            url = null,
            urlToImage = null,
            publishedAt = "",
            content = ""
        )

        coEvery { bookmarksRepository.addArticle(article) } returns Unit

        addArticleUseCase.invoke(article)

        coVerify(exactly = 1) { bookmarksRepository.addArticle(article) }
    }

    @Test
    fun `should add article to database`() = runTest {
        val expected = 1
        val testTitle = "test title"

        val article = ArticleModel(
            source = ArticleSourceModel(
                id = null,
                name = "Test AddArticle"
            ),
            author = null,
            title = testTitle,
            description = null,
            url = null,
            urlToImage = null,
            publishedAt = "",
            content = ""
        )

        addArticleUseCase.invoke(article)

        coEvery { bookmarksRepository.countByTitle(testTitle) } returns 1

        val result = countByTitleUseCase.invoke(testTitle)

        assertEquals(expected, result)

        coVerify(exactly = 1) { bookmarksRepository.countByTitle(testTitle) }
    }
}