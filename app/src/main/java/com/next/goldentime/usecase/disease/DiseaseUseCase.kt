package com.next.goldentime.usecase.disease

import com.next.goldentime.repository.disease.DiseaseRepository

class DiseaseUseCase(private val diseaseRepository: DiseaseRepository) {
    fun listDiseases(ids: Array<Int>? = null) = diseaseRepository.listDiseases(ids)
    fun getDisease(id: Int) = diseaseRepository.getDisease(id)
    fun getManual(diseaseId: Int) = diseaseRepository.getManual(diseaseId)
}