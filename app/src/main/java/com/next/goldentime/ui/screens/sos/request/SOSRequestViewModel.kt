package com.next.goldentime.ui.screens.sos.request

import androidx.lifecycle.ViewModel
import com.next.goldentime.usecase.patient.PatientUseCase
import com.next.goldentime.util.generatePatientUseCase

class SOSRequestViewModel(
    private val patientUseCase: PatientUseCase = generatePatientUseCase()
) : ViewModel() {
    val requestSOS = patientUseCase::requestSOS
}