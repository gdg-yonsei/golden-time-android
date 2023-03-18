package com.next.goldentime.ui.screens.sos.state

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.next.goldentime.App
import com.next.goldentime.R
import com.next.goldentime.repository.user.userStore
import com.next.goldentime.ui.components.common.Guide
import com.next.goldentime.ui.components.common.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SOSStateScreen(
    sosId: Int,
    completeSOS: () -> Unit,
    model: SOSStateViewModel = viewModel(
        factory = SOSStateViewModelFactory(sosId, App.context.userStore)
    )
) {
    Scaffold(topBar = { TopBar("Waiting for help") }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.image_location),
                contentDescription = null,
                modifier = Modifier.size(216.dp),
            )
            Spacer(Modifier.height(40.dp))
            Guide(description = "Users around you will receive an\nemergency SOS message.")
        }
    }
}