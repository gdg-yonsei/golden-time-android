package com.next.goldentime.usecase.rescue

import com.next.goldentime.repository.case.Case
import com.next.goldentime.repository.case.CaseRepository
import com.next.goldentime.repository.disease.DiseaseRepository
import com.next.goldentime.repository.location.Location
import com.next.goldentime.repository.location.LocationRepository
import com.next.goldentime.repository.sos.SOSRepository
import com.next.goldentime.usecase.profile.MedicalID
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

@OptIn(ExperimentalCoroutinesApi::class)
class RescueUseCase(
    private val sosRepository: SOSRepository,
    private val diseaseRepository: DiseaseRepository,
    private val caseRepository: CaseRepository,
    private val locationRepository: LocationRepository,
    private val sosId: Int,
) {
    private val _sosInfo = sosRepository.getSOSInfo(sosId)

    fun getLocation() = _sosInfo.map { it.location }
    fun getMedicalID() = _sosInfo.map { it.patient }.map {
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

    fun getCases() = _sosInfo
        .flatMapLatest { diseaseRepository.getDisease(it.patient.diseases[0]) }
        .flatMapLatest { disease ->
            flow {
                val cases = disease.cases.map { caseRepository.getCase(it.id).first() }
                emit(cases)
            }
        }.flowOn(Dispatchers.IO)

    fun getManual(case: Case) = case.manual

    suspend fun acceptSOS() = sosRepository.acceptSOS(sosId)
    suspend fun postLocation(): Location? {
        val location = locationRepository.getLocation().first() ?: return null
        sosRepository.postLocation(sosId, location)

        return location
    }

    suspend fun markAsArrived() = sosRepository.markAsArrived(sosId)
    suspend fun completeSOS() = sosRepository.completeSOS(sosId)
}