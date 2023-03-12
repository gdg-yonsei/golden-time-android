package com.next.goldentime.ui.screens.home.article.diseaseList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.next.goldentime.repository.disease.DiseaseStaticRepository
import com.next.goldentime.usecase.disease.DiseaseUseCase

class DiseaseListViewModel(
    private val diseaseUseCase: DiseaseUseCase = DiseaseUseCase(DiseaseStaticRepository())
) : ViewModel() {
    val diseases = diseaseUseCase.getDiseases().asLiveData()
}