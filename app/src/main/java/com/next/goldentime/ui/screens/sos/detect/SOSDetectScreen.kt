package com.next.goldentime.ui.screens.sos.detect

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
import com.next.goldentime.App
import com.next.goldentime.repository.user.userStore
import com.next.goldentime.ui.components.common.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SOSDetectScreen(
    confirmSOS: (sosId: Int) -> Unit,
    model: SOSDetectViewModel = viewModel(factory = SOSDetectViewModelFactory(App.context.userStore))
) {
    Scaffold(topBar = { TopBar("Fall Detected") }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("It looks like you've taken a hard fall")
            ElevatedButton(onClick = { confirmSOS(1) }) {
                Text("SOS Confirmed")
            }
        }
    }
}