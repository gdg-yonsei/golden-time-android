package com.next.goldentime.ui.screens.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.next.goldentime.data.MainNavigation
import com.next.goldentime.ui.components.main.fragment.ArticleFragment
import com.next.goldentime.ui.components.main.fragment.ProfileFragment
import com.next.goldentime.ui.components.main.fragment.SOSFragment

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    model: MainViewModel = viewModel()
) {
    val fragmentController = rememberNavController()
    val navList = model.navigationList

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("GOLDEN TIME") }, actions = {
                IconButton(onClick = { navController.navigate("about") }) {
                    Icon(imageVector = Icons.Filled.Info, contentDescription = "about")
                }
            })
        },
        bottomBar = {
            BottomNavigation(navController = fragmentController, navList = navList)
        }) {
        Surface(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            NavHost(
                navController = fragmentController,
                startDestination = MainNavigation.SOS.route
            ) {
                composable(MainNavigation.Profile.route) { ProfileFragment(model) }
                composable(MainNavigation.SOS.route) { SOSFragment() }
                composable(MainNavigation.Article.route) { ArticleFragment() }
            }
        }
    }
}

@Composable
fun BottomNavigation(navController: NavController, navList: List<MainNavigation>) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        navList.forEach { navigation ->
            NavigationBarItem(
                selected = navigation.route == currentRoute,
                onClick = {
                    navController.navigate(navigation.route) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) { saveState = true }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = navigation.icon,
                        contentDescription = navigation.label,
                    )
                },
                label = { Text(navigation.label) })
        }
    }
}
