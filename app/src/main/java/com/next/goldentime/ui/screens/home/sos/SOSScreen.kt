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
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.next.goldentime.App
import com.next.goldentime.repository.user.userStore
import com.next.goldentime.ui.RescueActivity
import com.next.goldentime.ui.SOSActivity

@Composable
fun SOSScreen(
    model: SOSViewModel = viewModel(factory = SOSViewModelFactory(App.context.userStore))
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("SOS Screen")
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