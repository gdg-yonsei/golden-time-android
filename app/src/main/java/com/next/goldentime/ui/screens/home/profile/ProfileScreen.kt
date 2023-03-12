package com.next.goldentime.ui.screens.home.profile

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.next.goldentime.App
import com.next.goldentime.repository.user.userStore
import com.next.goldentime.ui.screens.home.profile.edit.ProfileEditScreen
import com.next.goldentime.ui.screens.home.profile.view.ProfileViewScreen

@Composable
fun ProfileScreen(
    model: ProfileViewModel = viewModel(factory = ProfileViewModelFactory(App.context.userStore))
) {
    val navController = rememberNavController()

    fun navigateToProfileEdit() {
        navController.navigate(ProfileScreen.ProfileEdit.route)
    }

    fun navigateBack() {
        navController.navigateUp()
    }

    NavHost(navController = navController, startDestination = ProfileScreen.ProfileView.route) {
        composable(ProfileScreen.ProfileView.route) { ProfileViewScreen(navigateToProfileEdit = ::navigateToProfileEdit) }
        composable(ProfileScreen.ProfileEdit.route) { ProfileEditScreen(navigateBack = ::navigateBack) }
    }
}

private sealed class ProfileScreen(val route: String) {
    object ProfileView : ProfileScreen("profile/view")
    object ProfileEdit : ProfileScreen("profile/edit")
}