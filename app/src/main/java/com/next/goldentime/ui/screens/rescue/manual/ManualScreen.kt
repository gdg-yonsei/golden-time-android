package com.next.goldentime.ui.screens.rescue.manual

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.next.goldentime.ui.components.common.Suspender
import com.next.goldentime.ui.components.common.TopBar
import com.next.goldentime.ui.components.rescue.manual.ManualSheet
import com.next.goldentime.ui.screens.rescue.RescueViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManualScreen(
    showPatientID: () -> Unit,
    complete: () -> Unit,
    model: RescueViewModel,
) {
    val composeScope = rememberCoroutineScope()

    val location by model.location.observeAsState()
    val cases by model.cases.observeAsState()

    var location2 by remember {
        mutableStateOf<com.next.goldentime.repository.location.Location?>(null)
    }

    fun markAsArrived() {
        composeScope.launch {
            model.markAsArrived()
        }
    }

    LaunchedEffect(Unit) {
        composeScope.launch {
            val myLocation = model.postLocation()
            location2 = myLocation
        }
    }

    /**
     * Content
     */
    BottomSheetScaffold(
        topBar = { TopBar("Instructions") },
        sheetContent = {
            Suspender(cases) {
                ManualSheet(
                    manual = it[0].manual,
                    showPatientID = showPatientID,
                    markAsArrived = ::markAsArrived
                )
            }
        },
        sheetPeekHeight = 200.dp
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Suspender(location) { location ->
                Suspender(location2) { location2 ->
                    val patientLocation = LatLng(location.latitude, location.longitude)
                    val myLocation = LatLng(location2.latitude, location2.longitude)

                    val cameraPositionState = rememberCameraPositionState {
                        position = CameraPosition.fromLatLngZoom(patientLocation, 20f)
                    }

                    GoogleMap(
                        modifier = Modifier.fillMaxSize(),
                        cameraPositionState = cameraPositionState
                    ) {
                        Marker(
                            state = MarkerState(position = patientLocation),
                            title = "Patient here",
                            snippet = "Patient here"
                        )
                        Marker(
                            state = MarkerState(position = myLocation),
                            title = "I'm here",
                            snippet = "I'm here"
                        )
                    }
                }
            }
        }
    }
}