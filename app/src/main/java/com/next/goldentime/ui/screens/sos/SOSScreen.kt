package com.next.goldentime.ui.screens.sos

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.next.goldentime.ui.screens.sos.complete.SOSCompleteScreen
import com.next.goldentime.ui.screens.sos.detect.SOSDetectScreen
import com.next.goldentime.ui.screens.sos.request.SOSRequestScreen
import com.next.goldentime.ui.screens.sos.state.SOSStateScreen
import com.next.goldentime.usecase.patient.SOSType
import com.next.goldentime.util.removePrevious

@Composable
fun SOSScreen(sosType: SOSType, model: SOSViewModel = viewModel()) {
    val navController = rememberNavController()
    val context = LocalContext.current

    fun moveToSOSRequest() {
        navController.navigate(SOSScreen.SOSRequest.route) { removePrevious(navController) }
    }

    fun moveToSOSState(sosId: Int) {
        val route = SOSScreen.SOSState.route.replace("{sosId}", "$sosId")

        navController.navigate(route) { removePrevious(navController) }
    }

    fun moveToSOSComplete() {
        navController.navigate(SOSScreen.SOSComplete.route) { removePrevious(navController) }
    }

    fun finishSOS() {
        (context as Activity).finish()
    }

    /**
     * Content
     */
    NavHost(navController = navController, startDestination = SOSScreen.SOSDetect.route) {
        composable(SOSScreen.SOSDetect.route) {
            SOSDetectScreen(
                sosType = sosType,
                confirmSOS = ::moveToSOSRequest,
                cancelSOS = ::finishSOS
            )
        }
        composable(SOSScreen.SOSRequest.route) {
            SOSRequestScreen(showState = ::moveToSOSState, cancelSOS = ::finishSOS)
        }
        composable(SOSScreen.SOSState.route) {
            val sosId = it.arguments?.getString("sosId")?.toInt() ?: 0

            SOSStateScreen(sosId = sosId, completeSOS = ::moveToSOSComplete)
        }
        composable(SOSScreen.SOSComplete.route) {
            SOSCompleteScreen(finishSOS = ::finishSOS)
        }
    }
}

private sealed class SOSScreen(val route: String) {
    object SOSDetect : SOSScreen("sos/detect")
    object SOSRequest : SOSScreen("sos/request")
    object SOSState : SOSScreen("sos/{sosId}")
    object SOSComplete : SOSScreen("sos/complete")
}