package com.example.newsapp.data.mappers

import com.example.newsapp.data.model.ArticleModel
import com.example.newsapp.data.model.ArticleSourceModel
import com.example.newsapp.data.source.api.model.ArticleResponse
import com.example.newsapp.data.source.dao.model.ArticleEntity
import com.example.newsapp.data.source.dao.model.ArticleSourceEntity


fun ArticleResponse.asArticleModel(): ArticleModel {
    return ArticleModel(
        source = ArticleSourceModel(
            id = this.source.id,
            name = this.source.name
        ),
        author = this.author,
        title = this.title,
        description = this.description,
        url = this.url,
        urlToImage = this.urlToImage,
        publishedAt = this.publishedAt,
        content = this.content
    )
}

fun ArticleModel.asArticleEntity(): ArticleEntity {
    return ArticleEntity(
        source = ArticleSourceEntity(
            id = this.source.id,
            name = this.source.name
        ),
        author = this.author,
        title = this.title,
        description = this.description,
        url = this.url,
        urlToImage = this.urlToImage,
        publishedAt = this.publishedAt,
        content = this.content
    )
}

fun ArticleEntity.asArticleModel(): ArticleModel {
    return ArticleModel(
        source = ArticleSourceModel(
            id = this.source.id,
            name = this.source.name
        ),
        author = this.author,
        title = this.title,
        description = this.description,
        url = this.url,
        urlToImage = this.urlToImage,
        publishedAt = this.publishedAt,
        content = this.content
    )
}