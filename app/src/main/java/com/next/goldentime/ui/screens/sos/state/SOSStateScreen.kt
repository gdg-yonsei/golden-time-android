package com.next.goldentime.ui.screens.sos.state

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.next.goldentime.App
import com.next.goldentime.repository.profile.userStore
import com.next.goldentime.ui.components.common.TopBar
import com.next.goldentime.ui.components.effect.PreventBack
import com.next.goldentime.ui.components.sos.NoRescuerFragment
import com.next.goldentime.ui.components.sos.RescuerStateFragment
import com.next.goldentime.ui.components.sos.SOSWaiter

@Composable
fun SOSStateScreen(
    sosId: Int,
    completeSOS: () -> Unit,
    model: SOSStateViewModel = viewModel(
        factory = SOSStateViewModelFactory(sosId, App.context.userStore)
    )
) {
    PreventBack()

    Scaffold(topBar = { TopBar("Waiting for help") }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            val rescuerNum by model.rescuerNum.observeAsState(0)
            val closestRescuerDistance by model.closestRescuerDistance.observeAsState(-1.0)
            val done by model.done.observeAsState(false)

            LaunchedEffect(done) {
                if (done) completeSOS()
            }

            when (rescuerNum > 0 && closestRescuerDistance >= 0) {
                true -> RescuerStateFragment(
                    rescuerNum = rescuerNum,
                    closestRescuerDistance = closestRescuerDistance
                )
                false -> NoRescuerFragment()
            }

            SOSWaiter(completeSOS = completeSOS)
        }
    }
}