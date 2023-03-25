package com.next.goldentime.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.next.goldentime.ui.screens.sos.SOSScreen
import com.next.goldentime.ui.theme.GoldenTimeTheme
import com.next.goldentime.usecase.patient.SOSType

class SOSActivity : ComponentActivity() {
    private val sosType by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            intent.getSerializableExtra("sosType", SOSType::class.java)
        else
            intent.getSerializableExtra("sosType") as SOSType
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GoldenTimeTheme { SOSNavigation(sosType!!) }
        }
    }
}

@Composable
private fun SOSNavigation(sosType: SOSType) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = SOSNavigation.SOS.route) {
        composable(SOSNavigation.SOS.route) { SOSScreen(sosType = sosType) }
    }
}

private sealed class SOSNavigation(val route: String) {
    object SOS : SOSNavigation("sos")
}
