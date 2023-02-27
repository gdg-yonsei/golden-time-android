package com.next.goldentime.usecase.rescue

import com.next.goldentime.repository.sos.SOSRepository

class RescueUseCase(private val sosRepository: SOSRepository) {
    fun getSOS(id: Int) = sosRepository.getSOS(id)
}