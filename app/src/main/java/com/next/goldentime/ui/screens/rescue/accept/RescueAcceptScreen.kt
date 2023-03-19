package com.next.goldentime.ui.screens.rescue.accept

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.next.goldentime.ui.components.common.TopBar

@Composable
fun RescueAcceptScreen(accept: () -> Unit) {
    LaunchedEffect(Unit) {
        accept()
    }

    Scaffold(topBar = { TopBar("New SOS") }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}