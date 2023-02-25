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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.next.goldentime.ui.MainScreen
import com.next.goldentime.ui.components.main.fragment.ArticleFragment
import com.next.goldentime.ui.components.main.fragment.ProfileFragment
import com.next.goldentime.ui.components.main.fragment.SOSFragment

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateTo: (String) -> Unit,
    model: HomeViewModel = viewModel()
) {
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
        bottomBar = { BottomNavigation(navController = navController) }
    ) {
        NavHost(
            navController = navController,
            startDestination = HomeFragment.SOS.route,
            modifier = Modifier.padding(it)
        ) {
            composable(HomeFragment.Profile.route) { ProfileFragment(model) }
            composable(HomeFragment.SOS.route) { SOSFragment() }
            composable(HomeFragment.Article.route) { ArticleFragment() }
        }
    }
}

@Composable
fun BottomNavigation(navController: NavController) {
    val fragments = listOf(HomeFragment.Profile, HomeFragment.SOS, HomeFragment.Article)

    val currentNavigation by navController.currentBackStackEntryAsState()
    val currentRoute = currentNavigation?.destination?.route

    fun moveTo(route: String) {
        navController.navigate(route) {
            navController.graph.startDestinationRoute?.let {
                popUpTo(it) {
                    saveState = true
                    inclusive = true
                }
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    NavigationBar {
        fragments.forEach { fragment ->
            NavigationBarItem(
                selected = fragment.route == currentRoute,
                onClick = { moveTo(fragment.route) },
                icon = { Icon(imageVector = fragment.icon, contentDescription = fragment.label) },
                label = { Text(fragment.label) }
            )
        }
    }
}

private sealed class HomeFragment(val route: String, val label: String, val icon: ImageVector) {
    object Profile : HomeFragment("profile", "Profile", Icons.Filled.AccountCircle)
    object SOS : HomeFragment("sos", "SOS", Icons.Filled.Notifications)
    object Article : HomeFragment("article", "Article", Icons.Filled.Article)
}
