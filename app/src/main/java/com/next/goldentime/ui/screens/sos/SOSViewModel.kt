package com.next.goldentime.ui.screens.sos

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.next.goldentime.repository.sos.SOSStaticRepository
import com.next.goldentime.repository.user.UserStoreRepository
import com.next.goldentime.usecase.sos.SOSUseCase

class SOSViewModel(
    sosId: Int,
    userStore: DataStore<Preferences>,
    private val sosUseCase: SOSUseCase = SOSUseCase(
        SOSStaticRepository(),
        UserStoreRepository(userStore)
    )
) : ViewModel() {
    private val _sosState = sosUseCase.watchSOSState(sosId)

    val sosState = _sosState.asLiveData()
}

class SOSViewModelFactory(
    private val sosId: Int,
    private val userStore: DataStore<Preferences>
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SOSViewModel::class.java)) {
            return SOSViewModel(sosId, userStore) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}