package com.next.goldentime.ui.screens.home.sos

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.next.goldentime.ui.RescueActivity
import com.next.goldentime.ui.SOSActivity

@Composable
fun SOSScreen() {
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("SOS Fragment", color = Color.Gray)
        ElevatedButton(onClick = {
            context.startActivity(Intent(context, SOSActivity::class.java))
        }) {
            Text("Open SOS Activity")
        }
        ElevatedButton(onClick = {
            context.startActivity(Intent(context, RescueActivity::class.java))
        }) {
            Text("Open Rescue Activity")
        }
    }
}