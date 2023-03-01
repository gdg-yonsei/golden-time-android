package com.next.goldentime.usecase.profile

import com.next.goldentime.repository.user.Profile
import com.next.goldentime.repository.user.ProfileRepository

class ProfileUseCase(private val profileRepository: ProfileRepository) {
    fun watchProfile() = profileRepository.watchProfile()
    suspend fun setProfile(profile: Profile) = profileRepository.setProfile(profile)

    fun watchName() = profileRepository.watchName()
    fun watchBirthDate() = profileRepository.watchBirthDate()
    fun watchHeight() = profileRepository.watchHeight()
    fun watchWeight() = profileRepository.watchWeight()
    fun watchMedicalNotes() = profileRepository.watchMedicalNotes()
}