package com.next.goldentime.usecase.disease

import com.next.goldentime.repository.disease.DiseaseRepository

class DiseaseUseCase(private val diseaseRepository: DiseaseRepository) {
    fun getDiseases() = diseaseRepository.getDiseases()
    fun getDisease(id: Int) = diseaseRepository.getDisease(id)
}