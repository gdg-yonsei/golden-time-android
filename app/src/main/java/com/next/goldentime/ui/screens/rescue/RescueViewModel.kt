package com.next.goldentime.ui.screens.rescue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.next.goldentime.repository.disease.DiseaseStaticRepository
import com.next.goldentime.repository.sos.Location
import com.next.goldentime.repository.sos.SOSStaticRepository
import com.next.goldentime.usecase.rescue.RescueUseCase

class RescueViewModel(
    sosId: Int,
    private val rescueUseCase: RescueUseCase = RescueUseCase(
        SOSStaticRepository(),
        DiseaseStaticRepository(),
        sosId
    )
) : ViewModel() {
    fun getSOS() = rescueUseCase.getSOS().asLiveData()

    fun getManual(diseaseId: Int) = rescueUseCase.getManual(diseaseId).asLiveData()

    suspend fun acceptSOS() = rescueUseCase.acceptSOS()

    suspend fun postLocation(location: Location) = rescueUseCase.postLocation(location)

    suspend fun markAsArrived() = rescueUseCase.markAsArrived()

    suspend fun completeSOS() = rescueUseCase.completeSOS()
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