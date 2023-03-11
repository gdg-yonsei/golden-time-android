package com.next.goldentime.ui.screens.home.profile

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.next.goldentime.repository.disease.DiseaseStaticRepository
import com.next.goldentime.repository.user.User
import com.next.goldentime.repository.user.UserStoreRepository
import com.next.goldentime.usecase.user.UserUseCase
import java.util.*

class ProfileViewModel(
    userStore: DataStore<Preferences>,
    private val userUseCase: UserUseCase = UserUseCase(
        UserStoreRepository(userStore),
        DiseaseStaticRepository()
    )
) : ViewModel() {
    private val _user = userUseCase.watchUser()
    private val _name = userUseCase.watchName()

    val user = _user.asLiveData()
    val name = _name.asLiveData()

    suspend fun generateProfile() {
        val randomName = UUID.randomUUID().toString().substring(0, 5)
        userUseCase.setUser(User(randomName, "2000", 0.0, 0.0, "", "", "", "", listOf()))
    }
}

class ProfileViewModelFactory(
    private val userStore: DataStore<Preferences>
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(userStore) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}