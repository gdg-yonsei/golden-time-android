package com.next.goldentime.ui.screens.rescue.manual

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.next.goldentime.ui.components.common.Progress
import com.next.goldentime.ui.components.common.TopBar
import com.next.goldentime.ui.components.common.layout.Fill
import com.next.goldentime.ui.components.common.layout.Suspender
import com.next.goldentime.ui.components.rescue.manual.ManualSheet
import com.next.goldentime.ui.components.rescue.manual.RescueMap
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

    fun markAsArrived() {
        composeScope.launch {
            model.markAsArrived()
        }
    }

    fun completeSOS() {
        composeScope.launch {
            model.completeSOS()
            complete()
        }
    }

    /**
     * Content
     */
    BottomSheetScaffold(
        topBar = { TopBar("Instructions") },
        sheetContent = {
            Suspender(cases, { Fill { Progress() } }) {
                ManualSheet(
                    manual = model.getManual(it[0]),
                    showPatientID = showPatientID,
                    markAsArrived = ::markAsArrived,
                    complete = ::completeSOS
                )
            }
        },
        sheetPeekHeight = 200.dp,
    ) {
        Box(modifier = Modifier.padding(it)) {
            Suspender(location, { Fill { Progress() } }) { patientLocation ->
                RescueMap(
                    patientLocation = patientLocation,
                    postLocation = model.postLocation
                )
            }
        }
    }
}