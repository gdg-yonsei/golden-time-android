package com.next.goldentime.ui.screens.rescue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.next.goldentime.usecase.rescue.RescueUseCase
import com.next.goldentime.util.generateRescueUseCase

class RescueViewModel(
    sosId: Int,
    private val rescueUseCase: RescueUseCase = generateRescueUseCase(sosId)
) : ViewModel() {
    private val _location = rescueUseCase.getLocation()
    private val _medicalID = rescueUseCase.getMedicalID()
    private val _cases = rescueUseCase.getCases()
    
    val location = _location.asLiveData()
    val medicalID = _medicalID.asLiveData()
    val cases = _cases.asLiveData()

    val getManual = rescueUseCase::getManual

    val acceptSOS = rescueUseCase::acceptSOS
    val postLocation = rescueUseCase::postLocation
    val markAsArrived = rescueUseCase::markAsArrived
    val completeSOS = rescueUseCase::completeSOS
}

class RescueViewModelFactory(
    private val sosId: Int
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RescueViewModel::class.java)) {
            return RescueViewModel(sosId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}