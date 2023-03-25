package com.next.goldentime.ui.components.home.profile.read

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.next.goldentime.ui.components.common.MedicalIDReader
import com.next.goldentime.ui.components.common.Progress
import com.next.goldentime.ui.components.common.Validator
import com.next.goldentime.ui.components.common.layout.Fill
import com.next.goldentime.ui.components.common.layout.Suspender
import com.next.goldentime.ui.screens.home.profile.read.ProfileReadViewModel

@Composable
fun MedicalIDFragment(model: ProfileReadViewModel) {
    val medicalID by model.medicalID.observeAsState()

    Suspender(medicalID, { Fill { Progress() } }) {
        val medicalIDValid = model.checkMedicalIDValid(it)

        Validator(valid = medicalIDValid, invalidContent = { MedicalIDEditGuide() }) {
            MedicalIDReader(it)
        }
    }
}