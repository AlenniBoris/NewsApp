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

class CacheAddArticlesUseCaseTest {

    private lateinit var cacheAddArticlesUseCase: CacheAddArticlesUseCase
    private val cacheRepository: CacheRepository = mockk(relaxed = true)

    @Before
    fun setUp() {
        cacheAddArticlesUseCase = CacheAddArticlesUseCase(cacheRepository)
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

        coEvery { cacheRepository.addArticles(listOf(article)) } returns Unit

        cacheAddArticlesUseCase.invoke(listOf(article))

        coVerify(exactly = 1) { cacheRepository.addArticles(listOf(article)) }
    }

}