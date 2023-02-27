package com.next.goldentime.usecase.sos

import com.next.goldentime.repository.sos.SOSRepository

class SOSUseCase(private val sosRepository: SOSRepository) {
    suspend fun requestSOS() = sosRepository.postSOS()
}