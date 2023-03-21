package com.next.goldentime.repository.case

import kotlinx.coroutines.flow.Flow

data class Case(
    val id: Int,
    val title: String,
    val subtitle: String,
    val overview: String,
    val symptoms: List<String>,
    val causes: String,
    val manual: List<Step>,
)

data class SimpleCase(
    val id: Int,
    val title: String,
    val subtitle: String,
)

data class Step(val title: String, val description: String, val videoUrl: String? = null)

/**
 * Repository
 */
interface CaseRepository {
    fun listCases(): Flow<List<Case>>
    fun getCase(id: Int): Flow<Case>
}
