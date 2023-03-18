package com.next.goldentime.ui.screens.sos

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.next.goldentime.ui.screens.sos.complete.SOSCompleteScreen
import com.next.goldentime.ui.screens.sos.detect.SOSDetectScreen
import com.next.goldentime.ui.screens.sos.state.SOSStateScreen
import com.next.goldentime.usecase.sos.SOSType

@Composable
fun SOSScreen(type: SOSType, model: SOSViewModel = viewModel()) {
    val navController = rememberNavController()

    fun moveToSOSState(sosId: Int) {
        navController.navigate(
            SOSScreen.SOSState.route.replace(
                "{sosId}",
                "$sosId"
            )
        )
    }

    fun moveToSOSComplete() {
        navController.navigate(SOSScreen.SOSComplete.route)
    }

    NavHost(navController = navController, startDestination = SOSScreen.SOSDetect.route) {
        composable(SOSScreen.SOSDetect.route) {
            SOSDetectScreen(type = type, confirmSOS = ::moveToSOSState)
        }
        composable(SOSScreen.SOSState.route) {
            val sosId = it.arguments?.getString("sosId")?.toInt() ?: 0

            SOSStateScreen(sosId = sosId, completeSOS = ::moveToSOSComplete)
        }
        composable(SOSScreen.SOSComplete.route) { SOSCompleteScreen() }
    }
}

private sealed class SOSScreen(val route: String) {
    object SOSDetect : SOSScreen("sos/detect")
    object SOSState : SOSScreen("sos/{sosId}")
    object SOSComplete : SOSScreen("sos/complete")
}