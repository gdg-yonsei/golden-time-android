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
import com.next.goldentime.ui.components.common.Progress
import com.next.goldentime.ui.components.common.layout.Gap
import com.next.goldentime.ui.theme.Neutral80

@Composable
fun SOSWaiter(completeSOS: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(0.dp, 20.dp, 0.dp, 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(modifier = Modifier.size(150.dp), contentAlignment = Alignment.Center) {
            Progress()
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            FilledIconButton(
                modifier = Modifier.size(64.dp),
                colors = IconButtonDefaults.filledIconButtonColors(containerColor = Neutral80),
                onClick = { completeSOS() }
            ) {
                Icon(imageVector = Icons.Outlined.Close, contentDescription = null)
            }
            Gap(8)
            Text("I don't need a help")
        }
    }
}