package com.next.goldentime.ui.screens.sos.state

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.next.goldentime.repository.profile.UserStoreRepository
import com.next.goldentime.repository.sos.SOSStaticRepository
import com.next.goldentime.usecase.patient.SOSUseCase
import kotlinx.coroutines.flow.map

class SOSStateViewModel(
    sosId: Int,
    userStore: DataStore<Preferences>,
    private val sosUseCase: SOSUseCase = SOSUseCase(
        SOSStaticRepository(),
        UserStoreRepository(userStore)
    )
) : ViewModel() {
    private val _sosState = sosUseCase.watchSOSState(sosId)

    val rescuerNum = _sosState.map { it.rescuerNum }.asLiveData()
    val closestRescuerDistance = _sosState.map { it.closestRescuerDistance }.asLiveData()
    val done = _sosState.map { it.done }.asLiveData()
}

class SOSStateViewModelFactory(
    private val sosId: Int,
    private val userStore: DataStore<Preferences>
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SOSStateViewModel::class.java)) {
            return SOSStateViewModel(sosId, userStore) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}