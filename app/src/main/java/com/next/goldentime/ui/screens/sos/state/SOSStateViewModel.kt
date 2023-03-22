package com.next.goldentime.ui.screens.sos.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.next.goldentime.usecase.patient.PatientUseCase
import com.next.goldentime.util.generatePatientUseCase
import kotlinx.coroutines.flow.map

class SOSStateViewModel(
    sosId: Int,
    private val patientUseCase: PatientUseCase = generatePatientUseCase()
) : ViewModel() {
    private val _rescuer = patientUseCase.watchRescuer(sosId)

    val rescuerNum = _rescuer.map { it.rescuerNum }.asLiveData()
    val closestRescuerDistance = _rescuer.map { it.closestRescuerDistance }.asLiveData()
    val done = _rescuer.map { it.done }.asLiveData()
}

class SOSStateViewModelFactory(private val sosId: Int) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SOSStateViewModel::class.java)) {
            return SOSStateViewModel(sosId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}