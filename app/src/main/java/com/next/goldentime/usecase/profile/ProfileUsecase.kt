package com.next.goldentime.usecase.profile

import com.next.goldentime.repository.profile.ProfileRepository

class ProfileUsecase(private val profileRepository: ProfileRepository) {
    fun watchProfile() = profileRepository.watchProfile()

    fun watchName() = profileRepository.watchName()
    fun watchBirthDate() = profileRepository.watchBirthDate()
    fun watchHeight() = profileRepository.watchHeight()
    fun watchWeight() = profileRepository.watchWeight()
    fun watchMedicalNotes() = profileRepository.watchMedicalNotes()
}