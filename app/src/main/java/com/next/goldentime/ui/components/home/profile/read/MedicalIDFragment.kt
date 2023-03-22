package com.next.goldentime.ui.components.home.profile.read

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import com.next.goldentime.ui.components.common.MedicalIDReader
import com.next.goldentime.ui.components.common.Suspender
import com.next.goldentime.ui.components.common.Validator
import com.next.goldentime.usecase.profile.MedicalID

@Composable
fun MedicalIDFragment(
    liveMedicalID: LiveData<MedicalID>,
    checkMedicalIDValid: (medicalID: MedicalID) -> Boolean
) {
    val medicalID by liveMedicalID.observeAsState()

    Suspender(medicalID) {
        val medicalIDValid = checkMedicalIDValid(it)

        Validator(valid = medicalIDValid, invalidContent = { MedicalIDEditGuide() }) {
            MedicalIDReader(it)
        }
    }
}