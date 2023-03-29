package com.next.goldentime.ui.screens.sos.request

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.next.goldentime.ui.components.common.TopBar
import com.next.goldentime.ui.components.common.effect.PreventBack
import com.next.goldentime.ui.components.sos.state.NoRescuerFragment

@Composable
fun SOSRequestScreen(
    showState: (sosId: Int) -> Unit,
    cancelSOS: () -> Unit,
    model: SOSRequestViewModel = viewModel()
) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        val sosId = model.requestSOS()

        if (sosId != null) showState(sosId)
        else {
            Toast.makeText(
                context,
                "Failed to request SOS : It seems you don't turn on location on your phone.",
                Toast.LENGTH_LONG
            ).show()

            cancelSOS()
        }
    }

    PreventBack()

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