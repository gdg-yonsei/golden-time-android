package com.next.goldentime.usecase.user

import com.next.goldentime.repository.disease.DiseaseRepository
import com.next.goldentime.repository.user.User
import com.next.goldentime.repository.user.UserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map

class UserUseCase(
    private val userRepository: UserRepository,
    private val diseaseRepository: DiseaseRepository
) {
    fun watchUser() = userRepository.watchUser()
    fun watchName() = userRepository.watchName()

    @OptIn(ExperimentalCoroutinesApi::class)
    fun watchDiseases() = userRepository.watchDiseases().flatMapLatest { diseaseIds ->
        diseaseRepository.listDiseases(diseaseIds)
    }.map { it.diseases }

    suspend fun setUser(user: User) = userRepository.setUser(user)
    suspend fun setDiseases(diseases: List<Int>) = userRepository.setDiseases(diseases)
}