package com.next.goldentime.ui.screens.about

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.next.goldentime.ui.components.about.Section
import com.next.goldentime.ui.components.common.TopBar
import com.next.goldentime.ui.components.common.TopBarIcon
import com.next.goldentime.usecase.patient.SOSType

@Composable
fun AboutScreen(navigateBack: () -> Unit, model: AboutViewModel = viewModel()) {
    val context = LocalContext.current

    /**
     * Content
     */
    Scaffold(
        topBar = {
            TopBar("Settings", left = TopBarIcon(Icons.Outlined.ArrowBack) { navigateBack() })
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                Section(title = "About") {
                    Text("App version : 0.0.1")
                }
                Section(title = "Debug") {
                    OutlinedButton(onClick = { model.openRescueScreen(context, 1) }) {
                        Text("Open Rescue Screen")
                    }
                    OutlinedButton(onClick = { model.openSOSScreen(context, SOSType.FALL) }) {
                        Text("Open SOS Screen : Fall")
                    }
                    OutlinedButton(onClick = { model.openSOSScreen(context, SOSType.HEART) }) {
                        Text("Open SOS Screen : Heart")
                    }
                }
            }
        }
    }
}