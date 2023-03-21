package com.next.goldentime.usecase.rescue

import com.next.goldentime.repository.case.Case
import com.next.goldentime.repository.case.CaseRepository
import com.next.goldentime.repository.disease.DiseaseRepository
import com.next.goldentime.repository.sos.Location
import com.next.goldentime.repository.sos.SOSRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalCoroutinesApi::class)
class RescueUseCase(
    private val sosRepository: SOSRepository,
    private val diseaseRepository: DiseaseRepository,
    private val caseRepository: CaseRepository,
    private val sosId: Int,
) {
    private val _sosInfo = sosRepository.getSOSInfo(sosId)

    fun getLocation() = _sosInfo.map { it.location }
    fun getPatient() = _sosInfo.map { it.patient }
    fun getCases() = _sosInfo
        .flatMapLatest { diseaseRepository.getDisease(it.patient.diseases[0]) }
        .flatMapLatest { caseRepository.getCase(it.id) }.flowOn(Dispatchers.IO)

    fun getManual(case: Case) = case.manual

    suspend fun acceptSOS() = sosRepository.acceptSOS(sosId)
    suspend fun postLocation(location: Location) = sosRepository.postLocation(sosId, location)
    suspend fun markAsArrived() = sosRepository.markAsArrived(sosId)
    suspend fun completeSOS() = sosRepository.completeSOS(sosId)
}