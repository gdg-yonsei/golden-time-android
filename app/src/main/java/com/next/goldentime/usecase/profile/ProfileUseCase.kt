package com.next.goldentime.usecase.profile

import com.next.goldentime.repository.disease.DiseaseRepository
import com.next.goldentime.repository.profile.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

@OptIn(ExperimentalCoroutinesApi::class)
class ProfileUseCase(
    private val profileRepository: ProfileRepository,
    private val diseaseRepository: DiseaseRepository
) {
    private val _profile = profileRepository.watchProfile()

    fun watchMedicalID() = _profile.map {
        MedicalID(
            it.name,
            it.birthDate,
            it.height,
            it.weight,
            it.bloodType,
            it.allergies,
            it.medications,
            it.medicalNotes
        )
    }

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

    fun checkMedicalIDValid(medicalID: MedicalID): Boolean {
        val nameFilled = medicalID.name.isNotBlank()
        val birthDateFilled = medicalID.birthDate.isNotBlank()
        val heightFilled = medicalID.height > 0
        val weightFilled = medicalID.weight > 0
        val bloodTypeFilled = medicalID.bloodType.isNotBlank()

        return nameFilled && birthDateFilled && heightFilled && weightFilled && bloodTypeFilled
    }
}

data class MedicalID(
    val name: String,
    val birthDate: String,
    val height: Int,
    val weight: Int,
    val bloodType: String,
    val allergies: String,
    val medications: String,
    val medicalNotes: String,
)