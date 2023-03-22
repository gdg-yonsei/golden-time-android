package com.next.goldentime.ui.screens.home.profile

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.next.goldentime.ui.screens.home.profile.edit.ProfileEditScreen
import com.next.goldentime.ui.screens.home.profile.read.ProfileReadScreen

@Composable
fun ProfileScreen(model: ProfileViewModel = viewModel()) {
    val navController = rememberNavController()

    fun navigateToProfileEdit() {
        navController.navigate(ProfileScreen.ProfileEdit.route)
    }

    fun navigateBack() {
        navController.navigateUp()
    }

    /**
     * Content
     */
    NavHost(navController = navController, startDestination = ProfileScreen.ProfileRead.route) {
        composable(ProfileScreen.ProfileRead.route) {
            ProfileReadScreen(edit = ::navigateToProfileEdit)
        }
        composable(ProfileScreen.ProfileEdit.route) {
            ProfileEditScreen(navigateBack = ::navigateBack)
        }
    }
}

private sealed class ProfileScreen(val route: String) {
    object ProfileRead : ProfileScreen("profile/read")
    object ProfileEdit : ProfileScreen("profile/edit")
}