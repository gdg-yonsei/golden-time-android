package com.next.goldentime.usecase.article

import com.next.goldentime.repository.case.CaseRepository
import com.next.goldentime.repository.disease.DiseaseRepository

class ArticleUseCase(
    private val diseaseRepository: DiseaseRepository,
    private val caseRepository: CaseRepository,
) {
    fun listDiseases() = diseaseRepository.listDiseases()
    fun getDisease(id: Int) = diseaseRepository.getDisease(id)
    fun listCases() = caseRepository.listCases()
    fun getCase(id: Int) = caseRepository.getCase(id)
}