package com.next.goldentime.repository.article

import kotlinx.coroutines.flow.Flow

interface ArticleRepository {
    fun listArticles(): Flow<List<Article>>
    fun getArticle(id: Int): Flow<Article?>
    fun getManual(id: Int): Flow<List<String>?>
}

data class Article(
    val id: Int,
    val title: String,
    val subtitle: String,
    val overview: String,
    val manual: List<String>
)