package com.next.goldentime.ui.screens.home.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.next.goldentime.repository.disease.DiseaseAPIRepository
import com.next.goldentime.usecase.article.ArticleUseCase

class ArticleViewModel(
    private val articleUseCase: ArticleUseCase = ArticleUseCase(DiseaseAPIRepository())
) : ViewModel() {
    private val _articles = articleUseCase.listArticles()

    val articles = _articles.asLiveData()
}
