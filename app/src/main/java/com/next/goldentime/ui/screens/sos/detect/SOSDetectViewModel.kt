package com.next.goldentime.ui.screens.sos.detect

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.next.goldentime.repository.sos.Location
import com.next.goldentime.repository.sos.SOSStaticRepository
import com.next.goldentime.repository.user.UserStoreRepository
import com.next.goldentime.usecase.sos.SOSType
import com.next.goldentime.usecase.sos.SOSUseCase

class SOSDetectViewModel(
    userStore: DataStore<Preferences>,
    sosType: SOSType,
    private val sosUseCase: SOSUseCase = SOSUseCase(
        SOSStaticRepository(),
        UserStoreRepository(userStore)
    )
) : ViewModel() {
    val title = when (sosType) {
        SOSType.FALL -> "Fall Detected"
        SOSType.HEART -> "Irregular Heart Rate Detected"
        else -> "SOS"
    }

    val waitingTime = when (sosType) {
        SOSType.FALL, SOSType.HEART -> 30
        else -> 0
    }

    suspend fun requestSOS(location: Location) = sosUseCase.requestSOS(location)
}

class SOSDetectViewModelFactory(
    private val userStore: DataStore<Preferences>,
    private val sosType: SOSType
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SOSDetectViewModel::class.java)) {
            return SOSDetectViewModel(userStore, sosType) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}