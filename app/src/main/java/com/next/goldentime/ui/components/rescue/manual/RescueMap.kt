package com.next.goldentime.ui.components.rescue.manual

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.next.goldentime.repository.location.Location
import kotlinx.coroutines.delay

@Composable
fun RescueMap(
    patientLocation: Location,
    postLocation: suspend () -> Location?
) {
    val patientPosition = LatLng(patientLocation.latitude, patientLocation.longitude)
    var rescuerPosition by remember { mutableStateOf<LatLng?>(null) }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(patientPosition, 20f)
    }

    LaunchedEffect(Unit) {
        while (true) {
            val rescuerLocation = postLocation()
            rescuerLocation?.let { rescuerPosition = LatLng(it.latitude, it.longitude) }

            delay(3000)
        }
    }

    LaunchedEffect(rescuerPosition) {
        rescuerPosition?.let { rescuerPosition ->
            val bounds = LatLngBounds.builder()
                .include(patientPosition).include(rescuerPosition)
                .build()

            cameraPositionState.animate(CameraUpdateFactory.newLatLngBounds(bounds, 150))
        }
    }

    /**
     * Content
     */
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = MarkerState(position = patientPosition),
            title = "Patient here",
            snippet = "Patient here"
        )
        rescuerPosition?.let {
            Marker(
                state = MarkerState(position = it),
                title = "I'm here",
                snippet = "I'm here"
            )
        }
    }
}