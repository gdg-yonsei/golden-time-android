package com.next.goldentime.ui.screens.home.profile

import androidx.lifecycle.ViewModel
import com.next.goldentime.repository.profile.Profile
import com.next.goldentime.repository.profile.ProfileRepository

class ProfileViewModel : ViewModel() {
    private val profileRepository = ProfileRepository()

    val profile = profileRepository.watchProfile()

    fun updateProfile() {
        profileRepository.setProfile(Profile())
    }
}
