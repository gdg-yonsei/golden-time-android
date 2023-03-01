package com.next.goldentime.ui.screens.home.profile

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.next.goldentime.repository.user.Profile
import com.next.goldentime.repository.user.ProfileStoreRepository
import com.next.goldentime.usecase.profile.ProfileUseCase
import java.util.*

class ProfileViewModel(
    profileStore: DataStore<Preferences>,
    private val profileUseCase: ProfileUseCase = ProfileUseCase(ProfileStoreRepository(profileStore))
) : ViewModel() {
    private val _name = profileUseCase.watchName()

    val name = _name.asLiveData()

    suspend fun generateProfile() {
        val randomName = UUID.randomUUID().toString().substring(0, 5)
        profileUseCase.setProfile(Profile(randomName, "2000", 0.0, 0.0, ""))
    }
}

class ProfileViewModelFactory(
    private val profileStore: DataStore<Preferences>
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(profileStore) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}