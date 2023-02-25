package com.next.goldentime.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.next.goldentime.ui.screens.about.AboutScreen
import com.next.goldentime.ui.screens.home.HomeScreen
import com.next.goldentime.usecase.message.MessageUsecase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MessageUsecase.subscribeSOS()

        setContent { MainNavigation() }
    }
}

@Composable
private fun MainNavigation() {
    val navController = rememberNavController()

    fun navigateTo(route: String) {
        navController.navigate(route)
    }

    fun back() {
        navController.navigateUp()
    }

    NavHost(navController = navController, startDestination = MainScreen.Home.route) {
        composable(MainScreen.Home.route) {
            HomeScreen(navigateTo = ::navigateTo)
        }
        composable(MainScreen.About.route) {
            AboutScreen(back = ::back)
        }
    }
}

sealed class MainScreen(val route: String) {
    object Home : MainScreen("home")
    object About : MainScreen("about")
}
