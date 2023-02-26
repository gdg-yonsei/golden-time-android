package com.next.goldentime.ui.screens.home.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.next.goldentime.repository.article.ArticleStaticRepository
import com.next.goldentime.usecase.article.ArticleUseCase

class ArticleViewModel(
    private val articleUseCase: ArticleUseCase = ArticleUseCase(ArticleStaticRepository())
) : ViewModel() {
    private val _articles = articleUseCase.listArticles()

    val articles = _articles.asLiveData()
}
