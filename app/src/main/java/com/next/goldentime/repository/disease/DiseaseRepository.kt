package com.next.goldentime.repository.disease

import kotlinx.coroutines.flow.Flow

interface Disease {
    val title: String
    val subtitle: String
    val overview: String
    val manual: List<Step>
}

interface Step {
    val title: String
    val description: String
    val videoUrl: String?
}

/**
 * Repository
 */
interface DiseaseRepository {
    fun listDiseases(ids: Array<Int>?): Flow<ListDiseasesResponse>
    fun getDisease(id: Int): Flow<GetDiseaseResponse>
    fun getManual(diseaseId: Int): Flow<GetManualResponse>
}

interface ListDiseasesResponse {
    val diseases: List<Disease>
}

interface GetDiseaseResponse : Disease

interface GetManualResponse {
    val manual: List<Step>
}
