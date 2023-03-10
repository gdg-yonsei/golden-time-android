package com.next.goldentime.usecase.rescue

import com.next.goldentime.repository.disease.DiseaseRepository
import com.next.goldentime.repository.sos.Location
import com.next.goldentime.repository.sos.SOSRepository

class RescueUseCase(
    private val sosRepository: SOSRepository,
    private val diseaseRepository: DiseaseRepository,
    private val sosId: Int
) {
    fun getSOS() = sosRepository.getSOS(sosId)
    fun getManual(diseaseId: Int) = diseaseRepository.getManual(diseaseId)
    suspend fun acceptSOS() = sosRepository.acceptSOS(sosId)
    suspend fun postLocation(location: Location) = sosRepository.postLocation(sosId, location)
    suspend fun markAsArrived() = sosRepository.markAsArrived(sosId)
    suspend fun completeSOS() = sosRepository.completeSOS(sosId)
}