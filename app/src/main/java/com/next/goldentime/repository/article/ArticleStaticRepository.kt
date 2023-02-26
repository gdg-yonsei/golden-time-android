package com.next.goldentime.repository.article

import com.next.goldentime.data.articleFixtures
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class ArticleStaticRepository : ArticleRepository {
    private val _articles: Flow<List<Article>> = flow {
        emit(articleFixtures)
    }

    override fun listArticles() = _articles

    override fun getArticle(id: Int): Flow<Article?> {
        return _articles.map { it.find { article -> article.id == id } }
    }

    override fun getManual(id: Int): Flow<List<String>?> {
        return getArticle(id).map { it?.manual }
    }
}