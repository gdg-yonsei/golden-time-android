package com.next.goldentime.ui.screens.home.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.next.goldentime.repository.disease.DiseaseStaticRepository
import com.next.goldentime.usecase.disease.DiseaseUseCase

class ArticleViewModel(
    private val diseaseUseCase: DiseaseUseCase = DiseaseUseCase(DiseaseStaticRepository())
) : ViewModel() {
    private val _diseases = diseaseUseCase.getDiseases()

    val diseases = _diseases.asLiveData()
}
