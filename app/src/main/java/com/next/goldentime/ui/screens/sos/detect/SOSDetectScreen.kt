package com.next.goldentime.ui.screens.sos.detect

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import kotlinx.coroutines.launch

@Composable
fun SOSDetectScreen(
    sosType: SOSType,
    confirmSOS: (sosId: Int) -> Unit,
    cancelSOS: () -> Unit,
    model: SOSDetectViewModel = viewModel(factory = SOSDetectViewModelFactory(sosType))
) {
    val context = LocalContext.current
    val composeScope = rememberCoroutineScope()

    fun requestSOS() {
        Toast.makeText(context, "Finding your current location...", Toast.LENGTH_LONG).show()

        composeScope.launch {
            val sosId = model.requestSOS()
            confirmSOS(sosId)
        }
    }

    PreventBack()

    /**
     * Content
     */
    Scaffold(topBar = { TopBar(model.title) }) { it ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            Timer(duration = model.waitingTime, onComplete = { requestSOS() }) { remainingTime ->
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
                        TextButton("Help", true) { requestSOS() }
                    }
                }
            }
        }
    }
}