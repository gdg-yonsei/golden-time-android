package com.next.goldentime.ui.screens.home.article.diseaseList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.next.goldentime.repository.disease.DiseaseStaticRepository
import com.next.goldentime.usecase.article.ArticleUseCase

class DiseaseListViewModel(
    private val diseaseUseCase: ArticleUseCase = ArticleUseCase(DiseaseStaticRepository())
) : ViewModel() {
    val diseases = diseaseUseCase.getDiseases().asLiveData()
}