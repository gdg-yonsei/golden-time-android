package com.next.goldentime.ui.screens.sos.detect

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.next.goldentime.App
import com.next.goldentime.repository.user.userStore
import com.next.goldentime.ui.components.common.TopBar
import com.next.goldentime.ui.components.sos.DirectDetectGuide
import com.next.goldentime.ui.components.sos.FallDetectGuide
import com.next.goldentime.ui.components.sos.HeartDetectGuide
import com.next.goldentime.ui.components.sos.SOSTimer
import com.next.goldentime.usecase.sos.SOSType
import kotlinx.coroutines.delay


@Composable
fun SOSDetectScreen(
    type: SOSType,
    confirmSOS: (sosId: Int) -> Unit,
    model: SOSDetectViewModel = viewModel(factory = SOSDetectViewModelFactory(App.context.userStore))
) {
    val title = when (type) {
        SOSType.FALL -> "Fall Detected"
        SOSType.HEART -> "Irregular Heart rate Detected"
        else -> "SOS"
    }

    Scaffold(topBar = { TopBar(title) }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            var remainingTime by remember { mutableStateOf(30) }

            LaunchedEffect(Unit) {
                while (remainingTime > 0) {
                    delay(1000)
                    remainingTime--
                }
            }

            if (remainingTime > 25) {
                when (type) {
                    SOSType.FALL -> FallDetectGuide()
                    SOSType.HEART -> HeartDetectGuide()
                    else -> DirectDetectGuide()
                }
            } else {
                SOSTimer(remainingTime)
            }
        }
    }
}