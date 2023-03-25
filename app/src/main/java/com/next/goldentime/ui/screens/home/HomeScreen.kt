package com.next.goldentime.ui.screens.home

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.next.goldentime.ui.screens.home.article.ArticleScreen
import com.next.goldentime.ui.screens.home.profile.ProfileScreen
import com.next.goldentime.ui.screens.home.sos.SOSScreen

@Composable
fun HomeScreen(navigateToAbout: () -> Unit, model: HomeViewModel = viewModel()) {
    val context = LocalContext.current
    val navController = rememberNavController()

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val allGranted = permissions.values.all { it }

        if (!allGranted) (context as Activity).finish()
    }

    LaunchedEffect(Unit) {
        model.checkPermissions(context, permissionLauncher)
    }

    /**
     * Content
     */
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) {
        NavHost(
            navController = navController,
            startDestination = HomeScreen.SOS.route,
            modifier = Modifier.padding(it)
        ) {
            composable(HomeScreen.Profile.route) { ProfileScreen() }
            composable(HomeScreen.SOS.route) { SOSScreen(showAbout = navigateToAbout) }
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

    NavigationBar(
        containerColor = Color.White,
        modifier = Modifier.shadow(40.dp)
    ) {
        screens.forEach { screen ->
            NavigationBarItem(
                selected = screen.route == currentRoute,
                onClick = { moveTo(screen.route) },
                icon = { Icon(imageVector = screen.icon, contentDescription = screen.label) },
                label = { Text(screen.label) },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                    selectedIconColor = Color.Black
                )
            )
        }
    }
}

sealed class HomeScreen(val route: String, val label: String, val icon: ImageVector) {
    object Profile : HomeScreen("home/profile", "Profile", Icons.Filled.AccountCircle)
    object SOS : HomeScreen("home/sos", "SOS", Icons.Filled.Notifications)
    object Article : HomeScreen("home/article", "Article", Icons.Filled.Article)
}
