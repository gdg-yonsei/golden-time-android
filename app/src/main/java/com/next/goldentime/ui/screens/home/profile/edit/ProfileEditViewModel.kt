package com.next.goldentime.ui.screens.home.profile.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.next.goldentime.usecase.profile.ProfileUseCase
import com.next.goldentime.util.generateProfileUseCase
import kotlinx.coroutines.flow.first

class ProfileEditViewModel(
    private val profileUseCase: ProfileUseCase = generateProfileUseCase()
) : ViewModel() {
    private val _medicalID = profileUseCase.watchMedicalID()

    val medicalID = liveData { emit(_medicalID.first()) }

    val setMedicalID = profileUseCase::setMedicalID
    val checkMedicalIDValid = profileUseCase::checkMedicalIDValid
}