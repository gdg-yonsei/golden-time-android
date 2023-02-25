package com.next.goldentime.ui.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.next.goldentime.ui.MainScreen
import com.next.goldentime.ui.screens.home.article.ArticleScreen
import com.next.goldentime.ui.screens.home.profile.ProfileScreen
import com.next.goldentime.ui.screens.home.sos.SOSScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navigateTo: (String) -> Unit) {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("GOLDEN TIME") },
                actions = {
                    IconButton(onClick = { navigateTo(MainScreen.About.route) }) {
                        Icon(imageVector = Icons.Filled.Info, contentDescription = "about")
                    }
                }
            )
        },
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) {
        NavHost(
            navController = navController,
            startDestination = HomeScreen.SOS.route,
            modifier = Modifier.padding(it)
        ) {
            composable(HomeScreen.Profile.route) { ProfileScreen() }
            composable(HomeScreen.SOS.route) { SOSScreen() }
            composable(HomeScreen.Article.route) { ArticleScreen() }
        }
    }
}

@Composable
private fun BottomNavigationBar(navController: NavController) {
    val screens = listOf(HomeScreen.Profile, HomeScreen.SOS, HomeScreen.Article)

    val currentNavigation by navController.currentBackStackEntryAsState()
    val currentRoute = currentNavigation?.destination?.route

    fun moveTo(route: String) {
        navController.navigate(route) {
            navController.graph.startDestinationRoute?.let {
                popUpTo(it) { saveState = true }
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    NavigationBar {
        screens.forEach { screen ->
            NavigationBarItem(
                selected = screen.route == currentRoute,
                onClick = { moveTo(screen.route) },
                icon = { Icon(imageVector = screen.icon, contentDescription = screen.label) },
                label = { Text(screen.label) }
            )
        }
    }
}

sealed class HomeScreen(val route: String, val label: String, val icon: ImageVector) {
    object Profile : HomeScreen("profile", "Profile", Icons.Filled.AccountCircle)
    object SOS : HomeScreen("sos", "SOS", Icons.Filled.Notifications)
    object Article : HomeScreen("article", "Article", Icons.Filled.Article)
}
