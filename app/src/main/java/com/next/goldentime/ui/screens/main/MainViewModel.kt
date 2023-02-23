package com.next.goldentime.ui.screens.main

import androidx.lifecycle.ViewModel
import com.next.goldentime.data.MainNavigation
import com.next.goldentime.model.profile.Profile
import com.next.goldentime.model.profile.ProfileRepository

class MainViewModel : ViewModel() {
    private val profileRepository = ProfileRepository()

    val navigationList = listOf(MainNavigation.Profile, MainNavigation.SOS, MainNavigation.Article)
    val profile = profileRepository.watchProfile()

    fun updateProfile() {
        profileRepository.setProfile(Profile())
    }
}
