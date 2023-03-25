package com.next.goldentime.ui.screens.rescue.patient

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.next.goldentime.ui.components.common.MedicalIDReader
import com.next.goldentime.ui.components.common.Progress
import com.next.goldentime.ui.components.common.TopBar
import com.next.goldentime.ui.components.common.TopBarIcon
import com.next.goldentime.ui.components.common.layout.Fill
import com.next.goldentime.ui.components.common.layout.Suspender
import com.next.goldentime.ui.screens.rescue.RescueViewModel

@Composable
fun PatientScreen(navigateBack: () -> Unit, model: RescueViewModel) {
    /**
     * Content
     */
    Scaffold(topBar = {
        TopBar(
            "Medical ID",
            left = TopBarIcon(Icons.Outlined.ArrowBack) { navigateBack() }
        )
    }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            val medicalID by model.medicalID.observeAsState()

            Suspender(medicalID, { Fill { Progress() } }) {
                MedicalIDReader(it)
            }
        }
    }
}