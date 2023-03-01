package com.next.goldentime.repository.user

import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun watchUser(): Flow<User>
    fun watchName(): Flow<String>

    suspend fun setUser(user: User)
}

data class User(
    val name: String,
    val birthDate: String,
    val height: Double,
    val weight: Double,
    val bloodType: String,
    val allergies: String,
    val medications: String,
    val medicalNotes: String
)