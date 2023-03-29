package com.next.goldentime.ui.screens.rescue

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.next.goldentime.ui.screens.rescue.accept.RescueAcceptScreen
import com.next.goldentime.ui.screens.rescue.complete.RescueCompleteScreen
import com.next.goldentime.ui.screens.rescue.manual.ManualScreen
import com.next.goldentime.ui.screens.rescue.patient.PatientScreen
import com.next.goldentime.util.removePrevious

@Composable
fun RescueScreen(
    sosId: Int,
    model: RescueViewModel = viewModel(factory = RescueViewModelFactory(sosId))
) {
    val navController = rememberNavController()
    val context = LocalContext.current

    fun navigateToManual() {
        navController.navigate(RescueScreen.Manual.route) { removePrevious(navController) }
    }

    fun navigateToPatient() {
        navController.navigate(RescueScreen.Patient.route)
    }

    fun moveToComplete() {
        navController.navigate(RescueScreen.RescueComplete.route) { removePrevious(navController) }
    }

    fun navigateBack() {
        navController.navigateUp()
    }

    fun finish() {
        (context as Activity).finish()
    }

    /**
     * Content
     */
    NavHost(navController = navController, startDestination = RescueScreen.RescueAccept.route) {
        composable(RescueScreen.RescueAccept.route) {
            RescueAcceptScreen(
                showManual = ::navigateToManual,
                model = model
            )
        }
        composable(RescueScreen.Manual.route) {
            ManualScreen(
                showPatientID = ::navigateToPatient,
                complete = ::moveToComplete,
                model = model,
            )
        }
        composable(RescueScreen.Patient.route) {
            PatientScreen(
                navigateBack = ::navigateBack,
                model = model,
            )
        }
        composable(RescueScreen.RescueComplete.route) {
            RescueCompleteScreen(
                finish = ::finish,
                model = model,
            )
        }
    }
}

private sealed class RescueScreen(val route: String) {
    object RescueAccept : RescueScreen("rescue/accept")
    object Manual : RescueScreen("rescue/manual")
    object Patient : RescueScreen("rescue/patient")
    object RescueComplete : RescueScreen("rescue/complete")
}