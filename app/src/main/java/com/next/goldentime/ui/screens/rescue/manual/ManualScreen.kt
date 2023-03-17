package com.next.goldentime.ui.screens.rescue.manual

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.next.goldentime.ui.components.common.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManualScreen(
    showPatientID: () -> Unit,
    complete: () -> Unit,
    model: ManualViewModel = viewModel()
) {
    BottomSheetScaffold(
        topBar = { TopBar("Instructions") },
        sheetContent = {
            Column(modifier = Modifier.padding(24.dp)) {
                Text("Instructions")
                ElevatedButton(onClick = { /*TODO*/ }) {
                    Text("Call 911")
                }
                Text("Step1")
                Text("Step2")
                Text("Step3")
                Text("Step4")
                Text("Step5")
                Text("Step6")
            }
        }
    ) {
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