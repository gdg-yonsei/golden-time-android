package com.next.goldentime.repository.disease

import com.next.goldentime.repository.case.SimpleCase
import kotlinx.coroutines.flow.Flow

data class Disease(
    val id: Int,
    val title: String,
    val subtitle: String,
    val description: String,
    val cases: List<SimpleCase>,
)

/**
 * Repository
 */
interface DiseaseRepository {
    fun listDiseases(): Flow<List<Disease>>
    fun getDisease(id: Int): Flow<Disease>
}
