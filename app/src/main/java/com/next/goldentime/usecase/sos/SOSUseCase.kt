package com.next.goldentime.usecase.sos

import com.next.goldentime.repository.profile.UserRepository
import com.next.goldentime.repository.sos.Location
import com.next.goldentime.repository.sos.SOSRepository
import kotlinx.coroutines.flow.first

class SOSUseCase(
    private val sosRepository: SOSRepository,
    private val userRepository: UserRepository
) {
    suspend fun requestSOS(location: Location): Int {
        val user = userRepository.watchUser().first()
        return sosRepository.requestSOS(user, location)
    }

    fun watchSOSState(sosId: Int) = sosRepository.watchSOSState(sosId)
}

enum class SOSType {
    FALL, HEART, DIRECT
}
