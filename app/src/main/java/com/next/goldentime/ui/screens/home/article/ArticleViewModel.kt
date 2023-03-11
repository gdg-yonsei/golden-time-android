package com.next.goldentime.ui.screens.home.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.next.goldentime.repository.disease.DiseaseStaticRepository
import com.next.goldentime.usecase.disease.DiseaseUseCase

class ArticleViewModel(
    private val diseaseUseCase: DiseaseUseCase = DiseaseUseCase(DiseaseStaticRepository())
) : ViewModel() {
    fun getDiseases() = diseaseUseCase.getDiseases().asLiveData()
    fun getDisease(id: Int) = diseaseUseCase.getDisease(id).asLiveData()
}
