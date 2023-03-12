package com.next.goldentime.ui.screens.home.sos

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.next.goldentime.ui.components.common.TopBar
import com.next.goldentime.ui.components.common.TopBarIcon
import com.next.goldentime.ui.components.home.WithTopBar

@Composable
fun SOSScreen(
    navigateToAbout: () -> Unit,
    model: SOSViewModel = viewModel()
) {
    val context = LocalContext.current

    WithTopBar(
        topBar = {
            TopBar(
                "GOLDEN TIME",
                right = TopBarIcon(Icons.Outlined.Settings) { navigateToAbout() }
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedButton(
                onClick = { model.triggerSOS(context) },
                modifier = Modifier.size(200.dp),
                border = BorderStroke(2.5.dp, Color.Gray),
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    modifier = Modifier.size(40.dp),
                    tint = Color.Gray,
                )
            }
            Spacer(Modifier.height(20.dp))
            Text("SOS")
        }
    }
}