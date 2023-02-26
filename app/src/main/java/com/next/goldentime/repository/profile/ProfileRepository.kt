package com.next.goldentime.repository.profile

import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    fun watchProfile(): Flow<Profile>

    fun watchName(): Flow<String>
    fun watchBirthDate(): Flow<String>
    fun watchHeight(): Flow<Double>
    fun watchWeight(): Flow<Double>
    fun watchMedicalNotes(): Flow<String>
}

data class Profile(
    val name: String,
    val birthDate: String,
    val height: Double,
    val weight: Double,
    val medicalNotes: String
)