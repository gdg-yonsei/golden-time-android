package com.next.goldentime.ui.screens.map

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.next.goldentime.ui.RescueScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(navigateTo: (String) -> Unit) {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Map Screen")
            Spacer(modifier = Modifier.size(20.dp))
            ElevatedButton(onClick = { navigateTo(RescueScreen.Manual.route) }) {
                Text("I found the patient!")
            }
        }
    }
}