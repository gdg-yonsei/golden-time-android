package com.next.goldentime.ui.screens.sos

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.next.goldentime.App
import com.next.goldentime.repository.user.userStore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SOSScreen(
    sosId: Int,
    model: SOSViewModel = viewModel(factory = SOSViewModelFactory(sosId, App.context.userStore))
) {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text("SOS screen")
        }
    }
}