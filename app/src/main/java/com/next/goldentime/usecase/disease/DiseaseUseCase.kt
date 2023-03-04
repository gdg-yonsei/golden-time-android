package com.next.goldentime.usecase.disease

import com.next.goldentime.repository.disease.DiseaseRepository

class DiseaseUseCase(private val diseaseRepository: DiseaseRepository) {
    fun listDiseases() = diseaseRepository.listDiseases()
    fun getDisease(id: Int) = diseaseRepository.getDisease(id)
}