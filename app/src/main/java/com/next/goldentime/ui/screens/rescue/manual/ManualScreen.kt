package com.next.goldentime.ui.screens.rescue.manual

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.next.goldentime.ui.components.common.TopBar
import com.next.goldentime.ui.components.rescue.ManualSheet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManualScreen(
    showPatientID: () -> Unit,
    complete: () -> Unit
) {
    BottomSheetScaffold(
        topBar = { TopBar("Instructions") },
        sheetContent = { ManualSheet(showPatientID) },
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
            Text("Map")
        }
    }
}