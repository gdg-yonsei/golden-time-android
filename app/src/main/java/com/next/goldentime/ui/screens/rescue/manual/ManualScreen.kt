package com.next.goldentime.ui.screens.rescue.manual

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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

    fun markAsArrived() {
        composeScope.launch {
            model.markAsArrived()
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
        sheetPeekHeight = 100.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(Color.LightGray),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Suspender(location) {
                Text("Map")
            }
        }
    }
}