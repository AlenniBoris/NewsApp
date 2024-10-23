package com.example.newsapp.domain.usecase.cache

import com.example.newsapp.domain.model.ArticleModel
import com.example.newsapp.domain.model.ArticleSourceModel
import com.example.newsapp.domain.repository.CacheRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals

class CacheGetAllArticlesUseCaseTest {

    private lateinit var cacheAddArticlesUseCaseTest: CacheAddArticlesUseCase
    private lateinit var cacheGetAllArticlesUseCase: CacheGetAllArticlesUseCase
    private val cacheRepository: CacheRepository = mockk(relaxed = true)

    @Before
    fun setUp() {
        cacheAddArticlesUseCaseTest = CacheAddArticlesUseCase(cacheRepository)
        cacheGetAllArticlesUseCase = CacheGetAllArticlesUseCase(cacheRepository)
    }

    @Test
    fun `should call getAllBookmarks on repository`() = runTest {
        coEvery { cacheRepository.getAllArticles() } returns emptyList()

        cacheGetAllArticlesUseCase.invoke()

        coVerify(exactly = 1) { cacheRepository.getAllArticles() }
    }

    @Test
    fun `return empty list if nothing in database`() = runTest {
        coEvery { cacheRepository.getAllArticles() } returns emptyList()

        val result = cacheGetAllArticlesUseCase.invoke()

        assertEquals(0, result.size)

        coVerify(exactly = 1) { cacheRepository.getAllArticles() }
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

        cacheAddArticlesUseCaseTest.invoke(listOf(article1, article2))

        coEvery { cacheRepository.getAllArticles() } returns listOf(article1, article2)

        val result = cacheGetAllArticlesUseCase.invoke()

        assertEquals(2, result.size)

        coVerify(exactly = 1) { cacheRepository.getAllArticles() }
    }

}