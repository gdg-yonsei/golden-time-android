package com.next.goldentime.repository.disease

import kotlinx.coroutines.flow.Flow

data class Disease(
    val id: Int,
    val title: String,
    val subtitle: String,
    val overview: String,
    val manual: Manual,
)

typealias Manual = List<Step>

data class Step(val title: String, val description: String, val videoUrl: String?)

/**
 * Repository
 */
interface DiseaseRepository {
    fun getDiseases(ids: List<Int>? = null): Flow<List<Disease>>
    fun getDisease(id: Int): Flow<Disease>
    fun getManual(diseaseId: Int): Flow<Manual>
}
