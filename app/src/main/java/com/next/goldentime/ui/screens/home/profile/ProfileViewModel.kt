package com.next.goldentime.ui.screens.home.profile

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.next.goldentime.repository.profile.ProfileStoreRepository
import com.next.goldentime.usecase.profile.ProfileUsecase

class ProfileViewModel(
    profileStore: DataStore<Preferences>,
    profileUsecase: ProfileUsecase = ProfileUsecase(ProfileStoreRepository(profileStore))
) : ViewModel() {
    private val _name = profileUsecase.watchName()

    val name = _name.asLiveData()
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