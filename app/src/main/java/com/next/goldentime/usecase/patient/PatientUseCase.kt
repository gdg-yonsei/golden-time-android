package com.next.goldentime.usecase.patient

import com.next.goldentime.repository.location.LocationRepository
import com.next.goldentime.repository.profile.ProfileRepository
import com.next.goldentime.repository.sos.SOSRepository
import kotlinx.coroutines.flow.first

class PatientUseCase(
    private val sosRepository: SOSRepository,
    private val profileRepository: ProfileRepository,
    private val locationRepository: LocationRepository,
) {
    suspend fun requestSOS(): Int? {
        val profile = profileRepository.watchProfile().first()
        val location = locationRepository.getLocation().first() ?: return null

        return sosRepository.requestSOS(profile, location)
    }

    fun watchRescuer(sosId: Int) = sosRepository.watchSOSState(sosId)
}

enum class SOSType {
    FALL, HEART, DIRECT
}
