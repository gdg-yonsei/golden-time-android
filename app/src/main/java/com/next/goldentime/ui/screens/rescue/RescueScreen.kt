package com.next.goldentime.ui.screens.rescue

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.next.goldentime.ui.screens.rescue.accept.RescueAcceptScreen
import com.next.goldentime.ui.screens.rescue.complete.RescueCompleteScreen
import com.next.goldentime.ui.screens.rescue.manual.ManualScreen
import com.next.goldentime.ui.screens.rescue.patient.PatientScreen
import com.next.goldentime.util.removePrevious
import kotlinx.coroutines.launch

@Composable
fun RescueScreen(
    sosId: Int,
    model: RescueViewModel = viewModel(factory = RescueViewModelFactory(sosId))
) {
    val composeScope = rememberCoroutineScope()
    val navController = rememberNavController()

    fun acceptSOS() {
        composeScope.launch {
            model.acceptSOS()
            navController.navigate(RescueScreen.Manual.route) { removePrevious(navController) }
        }
    }

    fun navigateToPatient() {
        navController.navigate(RescueScreen.Patient.route)
    }

    fun moveToComplete() {
        navController.navigate(RescueScreen.RescueComplete.route)
    }

    fun navigateBack() {
        navController.navigateUp()
    }

    NavHost(navController = navController, startDestination = RescueScreen.RescueAccept.route) {
        composable(RescueScreen.RescueAccept.route) { RescueAcceptScreen(accept = ::acceptSOS) }
        composable(RescueScreen.Manual.route) {
            ManualScreen(
                model = model,
                showPatientID = ::navigateToPatient,
                complete = ::moveToComplete
            )
        }
        composable(RescueScreen.Patient.route) {
            PatientScreen(
                model = model,
                navigateBack = ::navigateBack
            )
        }
        composable(RescueScreen.RescueComplete.route) { RescueCompleteScreen() }
    }
}

private sealed class RescueScreen(val route: String) {
    object RescueAccept : RescueScreen("rescue/accept")
    object Manual : RescueScreen("rescue/manual")
    object Patient : RescueScreen("rescue/patient")
    object RescueComplete : RescueScreen("rescue/complete")
}