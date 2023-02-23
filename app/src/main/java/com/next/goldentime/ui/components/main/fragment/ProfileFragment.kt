package com.next.goldentime.ui.components.main.fragment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.next.goldentime.model.profile.Profile
import com.next.goldentime.ui.screens.main.MainViewModel

@Composable
fun ProfileFragment(model: MainViewModel) {
    val profile by model.profile.observeAsState(Profile())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Hello, ${profile.name}!", color = Color.White)
        Spacer(modifier = Modifier.size(20.dp))
        Button(onClick = { model.updateProfile() }) {
            Text("Update profile")
        }
    }
}