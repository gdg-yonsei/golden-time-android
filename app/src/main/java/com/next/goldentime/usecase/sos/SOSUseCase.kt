package com.next.goldentime.usecase.sos

import com.next.goldentime.repository.sos.Location
import com.next.goldentime.repository.sos.SOSRepository
import com.next.goldentime.repository.user.UserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapLatest

class SOSUseCase(
    private val sosRepository: SOSRepository,
    private val userRepository: UserRepository
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    fun requestSOS(location: Location) = userRepository.watchUser()
        .flatMapLatest { user -> sosRepository.requestSOS(user, location) }

    fun watchRescuers(sosId: Int) = sosRepository.watchRescuers(sosId)
}