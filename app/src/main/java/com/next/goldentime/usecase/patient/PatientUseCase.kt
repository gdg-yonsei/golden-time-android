package com.next.goldentime.usecase.patient

import com.next.goldentime.repository.profile.ProfileRepository
import com.next.goldentime.repository.sos.Location
import com.next.goldentime.repository.sos.SOSRepository
import kotlinx.coroutines.flow.first

class PatientUseCase(
    private val sosRepository: SOSRepository,
    private val profileRepository: ProfileRepository
) {
    suspend fun requestSOS(location: Location): Int {
        val profile = profileRepository.watchProfile().first()
        return sosRepository.requestSOS(profile, location)
    }

    fun watchRescuer(sosId: Int) = sosRepository.watchSOSState(sosId)
}

enum class SOSType {
    FALL, HEART, DIRECT
}
