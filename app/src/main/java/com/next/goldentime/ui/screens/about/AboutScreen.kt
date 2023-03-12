package com.next.goldentime.ui.screens.about

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.next.goldentime.ui.components.common.TopBar
import com.next.goldentime.ui.components.common.TopBarIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navigateBack: () -> Unit, model: AboutViewModel = viewModel()) {
    Scaffold(
        topBar = {
            TopBar("Settings", left = TopBarIcon(Icons.Outlined.ArrowBack) { navigateBack() })
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text("About")
        }
    }
}