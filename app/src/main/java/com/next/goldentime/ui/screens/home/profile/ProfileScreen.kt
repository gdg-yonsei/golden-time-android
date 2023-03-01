package com.next.goldentime.ui.screens.home.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.next.goldentime.App
import com.next.goldentime.repository.user.userStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    model: ProfileViewModel = viewModel(factory = ProfileViewModelFactory(App.context.userStore))
) {
    val composableScope: CoroutineScope = rememberCoroutineScope()

    val name by model.name.observeAsState("")

    fun generateProfile() {
        composableScope.launch { model.generateProfile() }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Hello, ${name}!")
        Spacer(modifier = Modifier.size(20.dp))
        ElevatedButton(onClick = { generateProfile() }) {
            Text("Generate new profile")
        }
    }
}