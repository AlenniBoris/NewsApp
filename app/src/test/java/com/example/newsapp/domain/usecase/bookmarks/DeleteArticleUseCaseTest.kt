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

class DeleteArticleUseCaseTest{

    private lateinit var addArticleUseCase: AddArticleUseCase
    private lateinit var deleteArticleUseCase: DeleteArticleUseCase
    private lateinit var countByTitleUseCase: CountByTitleUseCase
    private val bookmarksRepository: BookmarksRepository = mockk(relaxed = true)

    @Before
    fun setUp() {
        addArticleUseCase = AddArticleUseCase(bookmarksRepository)
        countByTitleUseCase = CountByTitleUseCase(bookmarksRepository)
        deleteArticleUseCase = DeleteArticleUseCase(bookmarksRepository)
    }

    @Test
    fun `should call deleteArticle on repository`() = runTest {

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


        addArticleUseCase.invoke(article)

        coEvery { bookmarksRepository.deleteArticle(article) } returns Unit

        deleteArticleUseCase.invoke(article)

        coVerify(exactly = 1) { bookmarksRepository.deleteArticle(article) }
    }

    @Test
    fun `should delete article from database`() = runTest {
        val expected = 0
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

        val beforeDelete = countByTitleUseCase.invoke(testTitle)
        assertEquals(1, beforeDelete)

        coVerify(exactly = 1) { bookmarksRepository.countByTitle(testTitle) }

        coEvery { bookmarksRepository.countByTitle(testTitle) } returns 0

        deleteArticleUseCase.invoke(article)

        val result = countByTitleUseCase.invoke(testTitle)
        assertEquals(expected, result)

    }

}