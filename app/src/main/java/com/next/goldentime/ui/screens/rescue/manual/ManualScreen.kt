package com.next.goldentime.ui.screens.rescue.manual

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.next.goldentime.ui.components.common.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManualScreen(
    showPatientID: () -> Unit,
    complete: () -> Unit,
    model: ManualViewModel = viewModel()
) {
    Scaffold(topBar = { TopBar("Instructions") }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ElevatedButton({ showPatientID() }) {
                Text("Patient ID")
            }
            ElevatedButton({ complete() }) {
                Text("Complete")
            }
        }
    }
}