package com.next.goldentime.ui.screens.rescue

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.next.goldentime.ui.screens.rescue.accept.AcceptScreen
import com.next.goldentime.ui.screens.rescue.complete.CompleteScreen
import com.next.goldentime.ui.screens.rescue.manual.ManualScreen
import com.next.goldentime.ui.screens.rescue.patient.PatientScreen

@Composable
fun RescueScreen(
    sosId: Int,
    model: RescueViewModel = viewModel(factory = RescueViewModelFactory(sosId))
) {
    val navController = rememberNavController()

    fun moveToManual() {
        navController.navigate(RescueScreen.Manual.route)
    }

    fun navigateToPatient() {
        navController.navigate(RescueScreen.Patient.route)
    }

    fun moveToComplete() {
        navController.navigate(RescueScreen.Complete.route)
    }

    fun navigateBack() {
        navController.navigateUp()
    }

    NavHost(navController = navController, startDestination = RescueScreen.Accept.route) {
        composable(RescueScreen.Accept.route) { AcceptScreen(accept = ::moveToManual) }
        composable(RescueScreen.Manual.route) {
            ManualScreen(showPatientID = ::navigateToPatient, complete = ::moveToComplete)
        }
        composable(RescueScreen.Patient.route) { PatientScreen(navigateBack = ::navigateBack) }
        composable(RescueScreen.Complete.route) { CompleteScreen() }
    }
}

private sealed class RescueScreen(val route: String) {
    object Accept : RescueScreen("rescue/accept")
    object Manual : RescueScreen("rescue/manual")
    object Patient : RescueScreen("rescue/patient")
    object Complete : RescueScreen("rescue/complete")
}