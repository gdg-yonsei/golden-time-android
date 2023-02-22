package com.next.goldentime.presentation.main

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.messaging.FirebaseMessaging
import com.next.goldentime.model.profile.Profile

class MainActivity : ComponentActivity() {
    private val model: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseMessaging.getInstance().subscribeToTopic("SOS").addOnCompleteListener { task ->
            val message =
                if (task.isSuccessful) "Successfully subscribed" else "Subscription failed"

            Log.d("GOLDEN TIME", message)
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }

        setContent {
            val profile by model.profile.observeAsState(Profile())

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(
                    space = 20.dp,
                    alignment = Alignment.CenterVertically
                ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("GOLDEN TIME")
                Text("Hello, ${profile.name}!")
                Button(onClick = { model.updateProfile() }) {
                    Text("Update profile")
                }
            }
        }
    }
}
