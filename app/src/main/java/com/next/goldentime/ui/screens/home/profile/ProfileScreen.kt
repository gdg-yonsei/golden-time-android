package com.next.goldentime.ui.screens.home.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.next.goldentime.repository.profile.Profile

@Composable
fun ProfileScreen(model: ProfileViewModel = viewModel()) {
    val profile by model.profile.observeAsState(Profile())

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Hello, ${profile.name}!")
        Spacer(modifier = Modifier.size(20.dp))
        Button(onClick = { model.updateProfile() }) {
            Text("Update profile")
        }
    }
}