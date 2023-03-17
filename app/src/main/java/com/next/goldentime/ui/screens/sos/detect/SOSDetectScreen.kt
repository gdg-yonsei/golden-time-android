package com.next.goldentime.ui.screens.sos.detect

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
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
            Image(
                painter = painterResource(id = R.drawable.image_falling),
                contentDescription = null,
                modifier = Modifier
                    .width(209.dp)
                    .height(240.dp),
            )
            Spacer(Modifier.height(28.dp))
            Guide(
                title = "It looks like you’ve taken a hard fall",
                description = "Do you need help? We will trigger\nEmergency SOS if you don’t respond."
            )
        }
    }
}