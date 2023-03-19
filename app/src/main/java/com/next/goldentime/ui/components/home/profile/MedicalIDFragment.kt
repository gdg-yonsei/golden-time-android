package com.next.goldentime.ui.components.home.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.next.goldentime.ui.components.common.Suspender
import com.next.goldentime.ui.components.common.Validator
import com.next.goldentime.ui.screens.home.profile.ProfileViewModel

@Composable
fun MedicalIDFragment(model: ProfileViewModel) {
    val user by model.user.observeAsState()

    Suspender(data = user) {
        val medicalIDValid = model.checkMedicalIDValid(it)

        Validator(valid = medicalIDValid, invalidContent = { MedicalIDEditGuide() }) {
            MedicalIDReader(it)
        }
    }
}