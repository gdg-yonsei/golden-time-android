package com.next.goldentime.ui.screens.rescue.patient

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.next.goldentime.ui.components.common.TopBar
import com.next.goldentime.ui.components.common.TopBarIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientScreen(navigateBack: () -> Unit, model: PatientViewModel = viewModel()) {
    Scaffold(topBar = {
        TopBar(
            "Medical ID",
            left = TopBarIcon(Icons.Outlined.ArrowBack) { navigateBack() }
        )
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Medical ID")
        }
    }
}