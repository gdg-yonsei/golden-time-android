package com.next.goldentime.ui.screens.sos

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.next.goldentime.ui.screens.sos.complete.CompleteScreen
import com.next.goldentime.ui.screens.sos.detect.DetectScreen
import com.next.goldentime.ui.screens.sos.state.StateScreen

@Composable
fun SOSScreen(model: SOSViewModel = viewModel()) {
    val navController = rememberNavController()

    fun moveToSOSState(sosId: Int) {
        navController.navigate(
            SOSScreen.State.route.replace(
                "{sosId}",
                "$sosId"
            )
        )
    }

    fun moveToSOSComplete() {
        navController.navigate(SOSScreen.Complete.route)
    }

    NavHost(navController = navController, startDestination = SOSScreen.Detect.route) {
        composable(SOSScreen.Detect.route) { DetectScreen(confirmSOS = ::moveToSOSState) }
        composable(SOSScreen.State.route) {
            val sosId = it.arguments?.getString("sosId")?.toInt() ?: 0

            StateScreen(sosId = sosId, completeSOS = ::moveToSOSComplete)
        }
        composable(SOSScreen.Complete.route) { CompleteScreen() }
    }
}

private sealed class SOSScreen(val route: String) {
    object Detect : SOSScreen("sos/detect")
    object State : SOSScreen("sos/{sosId}")
    object Complete : SOSScreen("sos/complete")
}