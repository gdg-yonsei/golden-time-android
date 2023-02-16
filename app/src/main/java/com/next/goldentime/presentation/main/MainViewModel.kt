package com.next.goldentime.presentation.main

import androidx.lifecycle.ViewModel
import com.next.goldentime.model.profile.Profile
import com.next.goldentime.model.profile.ProfileRepository

class MainViewModel : ViewModel() {
    private val profileRepository = ProfileRepository()

    val profile = profileRepository.watchProfile()

    fun updateProfile() {
        profileRepository.setProfile(Profile())
    }
}