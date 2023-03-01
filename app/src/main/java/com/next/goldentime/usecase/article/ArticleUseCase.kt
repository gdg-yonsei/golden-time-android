package com.next.goldentime.usecase.article

import com.next.goldentime.repository.disease.ArticleRepository

class ArticleUseCase(private val articleRepository: ArticleRepository) {
    fun listArticles() = articleRepository.listArticles()
    fun getArticle(id: Int) = articleRepository.getArticle(id)
    fun getManual(id: Int) = articleRepository.getManual(id)
}