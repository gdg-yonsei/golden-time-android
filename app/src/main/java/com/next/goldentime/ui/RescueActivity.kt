package com.next.goldentime.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.next.goldentime.ui.screens.manual.ManualScreen
import com.next.goldentime.ui.screens.map.MapScreen

class RescueActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent { RescueNavigation() }
    }
}

@Composable
private fun RescueNavigation() {
    val navController = rememberNavController()

    fun navigateTo(route: String) {
        navController.navigate(route)
    }

    fun back() {
        navController.navigateUp()
    }

    NavHost(navController = navController, startDestination = RescueScreen.Map.route) {
        composable(RescueScreen.Map.route) { MapScreen(navigateTo = ::navigateTo) }
        composable(RescueScreen.Manual.route) { ManualScreen(back = ::back) }
    }
}

sealed class RescueScreen(val route: String) {
    object Map : RescueScreen("map")
    object Manual : RescueScreen("manual")
}
