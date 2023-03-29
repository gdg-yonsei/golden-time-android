package com.next.goldentime.ui.screens.sos.detect

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.next.goldentime.ui.components.common.TopBar
import com.next.goldentime.ui.components.common.button.TextButton
import com.next.goldentime.ui.components.common.effect.PreventBack
import com.next.goldentime.ui.components.common.effect.Timer
import com.next.goldentime.ui.components.common.layout.Fill
import com.next.goldentime.ui.components.sos.detect.FallDetectGuide
import com.next.goldentime.ui.components.sos.detect.HeartDetectGuide
import com.next.goldentime.ui.components.sos.detect.SOSTimer
import com.next.goldentime.usecase.patient.SOSType

@Composable
fun SOSDetectScreen(
    sosType: SOSType,
    confirmSOS: () -> Unit,
    cancelSOS: () -> Unit,
    model: SOSDetectViewModel = viewModel(factory = SOSDetectViewModelFactory(sosType))
) {
    PreventBack()

    /**
     * Content
     */
    Scaffold(topBar = { TopBar(model.title) }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            Timer(duration = model.waitingTime, onComplete = { confirmSOS() }) { remainingTime ->
                Fill {
                    when {
                        remainingTime < 25 -> SOSTimer(remainingTime)
                        sosType === SOSType.FALL -> FallDetectGuide()
                        sosType === SOSType.HEART -> HeartDetectGuide()
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .padding(40.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        TextButton("Cancel") { cancelSOS() }
                        TextButton("Help", true) { confirmSOS() }
                    }
                }
            }
        }
    }
}