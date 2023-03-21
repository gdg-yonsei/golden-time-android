package com.next.goldentime.ui.screens.home.profile

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.next.goldentime.repository.disease.DiseaseStaticRepository
import com.next.goldentime.repository.profile.User
import com.next.goldentime.repository.profile.UserStoreRepository
import com.next.goldentime.usecase.user.UserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import java.util.*

class ProfileViewModel(
    userStore: DataStore<Preferences>,
    private val userUseCase: UserUseCase = UserUseCase(
        UserStoreRepository(userStore),
        DiseaseStaticRepository()
    )
) : ViewModel() {
    private val _user = userUseCase.watchUser()
    private val _diseases = userUseCase.watchDiseases()

    val user = _user.asLiveData(Dispatchers.IO)
    val diseases = _diseases.asLiveData(Dispatchers.IO)
    suspend fun getUser() = _user.first()

    fun checkMedicalIDValid(user: User): Boolean {
        val nameFilled = user.name.isNotBlank()
        val birthDateFilled = user.birthDate.isNotBlank()
        val heightFilled = user.height > 0.0
        val weightFilled = user.weight > 0.0
        val bloodTypeFilled = user.bloodType.isNotBlank()

        return nameFilled && birthDateFilled && heightFilled && weightFilled && bloodTypeFilled
    }

    suspend fun saveMedicalID(user: User) = userUseCase.setUser(user)
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