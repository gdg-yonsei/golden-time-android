package com.next.goldentime.ui.screens.home.profile.read

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.next.goldentime.usecase.profile.ProfileUseCase
import com.next.goldentime.util.generateProfileUseCase

class ProfileReadViewModel(
    private val profileUseCase: ProfileUseCase = generateProfileUseCase()
) : ViewModel() {
    private val _medicalID = profileUseCase.watchMedicalID()
    private val _diseases = profileUseCase.watchDiseases()

    val medicalID = _medicalID.asLiveData()
    val diseases = _diseases.asLiveData()

    val checkMedicalIDValid = profileUseCase::checkMedicalIDValid
}