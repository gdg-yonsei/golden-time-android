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
import com.next.goldentime.ui.theme.GoldenTimeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GoldenTimeTheme { MainNavigation() }
        }
    }
}

@Composable
private fun MainNavigation() {
    val navController = rememberNavController()

    fun navigateToAbout() {
        navController.navigate(MainNavigation.About.route)
    }

    fun navigateBack() {
        navController.navigateUp()
    }

    NavHost(navController = navController, startDestination = MainNavigation.Home.route) {
        composable(MainNavigation.Home.route) { HomeScreen(navigateToAbout = ::navigateToAbout) }
        composable(MainNavigation.About.route) { AboutScreen(navigateBack = ::navigateBack) }
    }
}

private sealed class MainNavigation(val route: String) {
    object Home : MainNavigation("home")
    object About : MainNavigation("about")
}
