package com.next.goldentime.ui.screens.sos.detect

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Emergency
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.next.goldentime.App
import com.next.goldentime.repository.profile.userStore
import com.next.goldentime.repository.sos.Location
import com.next.goldentime.ui.components.common.ChipButton
import com.next.goldentime.ui.components.common.Timer
import com.next.goldentime.ui.components.common.TopBar
import com.next.goldentime.ui.components.effect.PreventBack
import com.next.goldentime.ui.components.sos.FallDetectGuide
import com.next.goldentime.ui.components.sos.HeartDetectGuide
import com.next.goldentime.ui.components.sos.SOSTimer
import com.next.goldentime.usecase.sos.SOSType
import kotlinx.coroutines.launch

@Composable
fun SOSDetectScreen(
    sosType: SOSType,
    confirmSOS: (sosId: Int) -> Unit,
    cancelSOS: () -> Unit,
    model: SOSDetectViewModel = viewModel(
        factory = SOSDetectViewModelFactory(App.context.userStore, sosType)
    )
) {
    val composeScope = rememberCoroutineScope()

    fun requestSOS() {
        composeScope.launch {
            val sosId = model.requestSOS(Location(0.0, 0.0))
            confirmSOS(sosId)
        }
    }

    PreventBack()

    Scaffold(topBar = { TopBar(model.title) }) { it ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            Timer(duration = model.waitingTime, onComplete = { requestSOS() }) { remainingTime ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    when {
                        remainingTime < 25 -> SOSTimer(remainingTime)
                        sosType === SOSType.FALL -> FallDetectGuide()
                        sosType === SOSType.HEART -> HeartDetectGuide()
                    }
                    Spacer(Modifier.height(28.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                        ChipButton(label = "I need a help", icon = Icons.Filled.Emergency) {
                            requestSOS()
                        }
                        ChipButton(label = "I'm OK", icon = Icons.Outlined.Check) {
                            cancelSOS()
                        }
                    }
                }
            }
        }
    }
}