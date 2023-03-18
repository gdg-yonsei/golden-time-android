package com.next.goldentime.ui.screens.rescue.accept

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.next.goldentime.ui.components.common.Timer
import com.next.goldentime.ui.components.common.TopBar

@Composable
fun RescueAcceptScreen(accept: () -> Unit) {
    Scaffold(topBar = { TopBar("New SOS") }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            Timer(duration = 1, onComplete = { accept() }) {
                CircularProgressIndicator()
            }
        }
    }
}