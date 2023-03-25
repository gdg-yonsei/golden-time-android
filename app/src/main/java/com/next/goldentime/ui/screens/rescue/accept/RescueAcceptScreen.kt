package com.next.goldentime.ui.screens.rescue.accept

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.next.goldentime.ui.components.common.Progress
import com.next.goldentime.ui.components.common.TopBar
import com.next.goldentime.ui.components.common.layout.Fill
import com.next.goldentime.ui.screens.rescue.RescueViewModel

@Composable
fun RescueAcceptScreen(showManual: () -> Unit, model: RescueViewModel) {
    LaunchedEffect(Unit) {
        model.acceptSOS()
        showManual()
    }

    /**
     * Content
     */
    Scaffold(topBar = { TopBar("New SOS") }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            Fill { Progress() }
        }
    }
}