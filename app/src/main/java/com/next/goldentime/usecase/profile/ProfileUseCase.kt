package com.next.goldentime.usecase.profile

import com.next.goldentime.repository.disease.DiseaseRepository
import com.next.goldentime.repository.profile.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

@OptIn(ExperimentalCoroutinesApi::class)
class ProfileUseCase(
    private val profileRepository: ProfileRepository,
    private val diseaseRepository: DiseaseRepository
) {
    private val _profile = profileRepository.watchProfile()

    fun watchProfile() = _profile
    fun watchDiseases() = _profile.flatMapLatest { profile ->
        flow {
            val diseases = profile.diseases.map { diseaseRepository.getDisease(it).first() }
            emit(diseases)
        }
    }.flowOn(Dispatchers.IO)

    suspend fun setMedicalID(
        name: String,
        birthDate: String,
        height: Int,
        weight: Int,
        bloodType: String,
        allergies: String,
        medications: String,
        medicalNotes: String,
    ) = profileRepository.setProfile(
        name,
        birthDate,
        height,
        weight,
        bloodType,
        allergies,
        medications,
        medicalNotes
    )

    suspend fun setDiseases(diseases: List<Int>) = profileRepository.setProfile(diseases = diseases)
}