package com.next.goldentime.ui.screens.sos.request

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.next.goldentime.ui.components.common.TopBar
import com.next.goldentime.ui.components.common.effect.PreventBack
import com.next.goldentime.ui.components.sos.state.NoRescuerFragment

@Composable
fun SOSRequestScreen(
    showState: (sosId: Int) -> Unit,
    model: SOSRequestViewModel = viewModel()
) {
    PreventBack()

    LaunchedEffect(Unit) {
        val sosId = model.requestSOS()
        showState(sosId)
    }

    /**
     * Content
     */
    Scaffold(topBar = { TopBar("Requesting SOS") }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            NoRescuerFragment()
        }
    }
}