package com.next.goldentime.ui.screens.sos.detect

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.next.goldentime.usecase.patient.PatientUseCase
import com.next.goldentime.usecase.patient.SOSType
import com.next.goldentime.util.generatePatientUseCase

class SOSDetectViewModel(
    sosType: SOSType,
    private val patientUseCase: PatientUseCase = generatePatientUseCase()
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

    val requestSOS = patientUseCase::requestSOS
}

class SOSDetectViewModelFactory(private val sosType: SOSType) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SOSDetectViewModel::class.java)) {
            return SOSDetectViewModel(sosType) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}