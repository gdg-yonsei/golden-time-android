package com.next.goldentime.ui.components.sos.state

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SOSWaiter(completeSOS: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(0.dp, 20.dp, 0.dp, 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        CircularProgressIndicator()
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FilledIconButton(
                modifier = Modifier.size(64.dp),
                colors = IconButtonDefaults.filledIconButtonColors(containerColor = Color(0xFFD0C4C0)),
                onClick = { completeSOS() }
            ) {
                Icon(imageVector = Icons.Outlined.Close, contentDescription = null)
            }
            Text("I don't need a help")
        }
    }
}