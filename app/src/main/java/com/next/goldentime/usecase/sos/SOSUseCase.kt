package com.next.goldentime.usecase.sos

import com.next.goldentime.repository.sos.Location
import com.next.goldentime.repository.sos.RequestSOSResponse
import com.next.goldentime.repository.sos.SOSRepository
import com.next.goldentime.repository.user.UserRepository
import kotlinx.coroutines.flow.first

class SOSUseCase(
    private val sosRepository: SOSRepository,
    private val userRepository: UserRepository
) {
    suspend fun requestSOS(location: Location): RequestSOSResponse {
        val user = userRepository.watchUser().first()
        return sosRepository.requestSOS(user, location)
    }

    fun watchRescuers(sosId: Int) = sosRepository.watchRescuers(sosId)
}