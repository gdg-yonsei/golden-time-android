package com.next.goldentime.ui.screens.rescue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.next.goldentime.repository.disease.DiseaseStaticRepository
import com.next.goldentime.repository.sos.SOSStaticRepository
import com.next.goldentime.usecase.rescue.RescueUseCase

class RescueViewModel(
    sosId: Int,
    private val rescueUseCase: RescueUseCase = RescueUseCase(
        SOSStaticRepository(),
        DiseaseStaticRepository(),
        sosId
    )
) : ViewModel()

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