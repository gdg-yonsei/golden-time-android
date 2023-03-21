package com.next.goldentime.usecase.user

import com.next.goldentime.repository.disease.DiseaseRepository
import com.next.goldentime.repository.profile.User
import com.next.goldentime.repository.profile.UserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapLatest

class UserUseCase(
    private val userRepository: UserRepository,
    private val diseaseRepository: DiseaseRepository
) {
    fun watchUser() = userRepository.watchUser()
    fun watchName() = userRepository.watchName()

    @OptIn(ExperimentalCoroutinesApi::class)
    fun watchDiseases() = userRepository.watchDiseases().flatMapLatest { diseaseIds ->
        diseaseRepository.getDiseases(diseaseIds)
    }

    suspend fun setUser(user: User) = userRepository.setUser(user)
    suspend fun setDiseases(diseases: List<Int>) = userRepository.setDiseases(diseases)
}