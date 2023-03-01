package com.next.goldentime.ui.screens.home.profile

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.next.goldentime.repository.user.User
import com.next.goldentime.repository.user.UserStoreRepository
import com.next.goldentime.usecase.user.UserUseCase
import java.util.*

class ProfileViewModel(
    profileStore: DataStore<Preferences>,
    private val userUseCase: UserUseCase = UserUseCase(UserStoreRepository(profileStore))
) : ViewModel() {
    private val _name = userUseCase.watchName()

    val name = _name.asLiveData()

    suspend fun generateProfile() {
        val randomName = UUID.randomUUID().toString().substring(0, 5)
        userUseCase.setUser(User(randomName, "2000", 0.0, 0.0, "", "", "", ""))
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