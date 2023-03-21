package com.next.goldentime.repository.profile

import kotlinx.coroutines.flow.Flow

data class Profile(
    val name: String,
    val birthDate: String,
    val height: Int,
    val weight: Int,
    val bloodType: String,
    val allergies: String,
    val medications: String,
    val medicalNotes: String,
    val diseases: List<Int>,
)

/**
 * Repository
 */
interface ProfileRepository {
    fun watchProfile(): Flow<Profile>

    suspend fun setProfile(
        name: String? = null,
        birthDate: String? = null,
        height: Int? = null,
        weight: Int? = null,
        bloodType: String? = null,
        allergies: String? = null,
        medications: String? = null,
        medicalNotes: String? = null,
        diseases: List<Int>? = null
    )
}
