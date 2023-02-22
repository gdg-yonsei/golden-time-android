package com.next.goldentime.presentation.main

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.messaging.FirebaseMessaging
import com.next.goldentime.model.profile.Profile

class MainActivity : ComponentActivity() {
    private val model: MainViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
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

            Scaffold(
                topBar = {
                    TopAppBar(title = { Text("GOLDEN TIME") })
                },
                content = {
                    LazyColumn(
                        contentPadding = it,
                        modifier = Modifier
                            .padding(20.dp)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(
                            space = 20.dp,
                            alignment = Alignment.CenterVertically
                        ),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        item {
                            Text("Hello, ${profile.name}!")
                            Button(onClick = { model.updateProfile() }) {
                                Text("Update profile")
                            }
                        }
                    }
                }
            )
        }
    }
}
