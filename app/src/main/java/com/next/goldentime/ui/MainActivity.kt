package com.next.goldentime.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.messaging.FirebaseMessaging
import com.next.goldentime.ui.screens.about.AboutScreen
import com.next.goldentime.ui.screens.main.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseMessaging.getInstance().subscribeToTopic("SOS").addOnCompleteListener { task ->
            val message =
                if (task.isSuccessful) "Successfully subscribed" else "Subscription failed"

            Log.d("GOLDEN TIME", message)
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }

        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "main") {
                composable("main") { MainScreen(navController) }
                composable("about") { AboutScreen() }
            }
        }
    }
}

