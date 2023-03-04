package com.next.goldentime.repository.user

import kotlinx.coroutines.flow.Flow

data class User(
    val name: String,
    val birthDate: String,
    val height: Double,
    val weight: Double,
    val bloodType: String,
    val allergies: String,
    val medications: String,
    val medicalNotes: String,
    val diseases: List<Int>,
)

/**
 * Repository
 */
interface UserRepository {
    fun watchUser(): Flow<User>
    fun watchName(): Flow<String>
    fun watchDiseases(): Flow<List<Int>>

    suspend fun setUser(user: User)
    suspend fun setDiseases(diseases: List<Int>)
}
