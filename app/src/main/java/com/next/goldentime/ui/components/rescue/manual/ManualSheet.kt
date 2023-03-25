package com.next.goldentime.ui.components.rescue.manual

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.next.goldentime.repository.case.Step
import com.next.goldentime.ui.components.common.button.ChipButton
import com.next.goldentime.ui.components.common.layout.Gap

typealias Manual = List<Step>

@Composable
fun ManualSheet(manual: Manual, showPatientID: () -> Unit, markAsArrived: () -> Unit) {
    Column(modifier = Modifier.padding(24.dp)) {
        Text(
            "Instructions",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground
        )

        Row(horizontalArrangement = Arrangement.spacedBy(7.dp)) {
            ChipButton(label = "Call 911", icon = Icons.Filled.Call) {
                Log.d("GOLDEN TIME", "CALL to 911")
            }
            ChipButton(label = "Medical ID", icon = Icons.Filled.LocalHospital) {
                showPatientID()
            }
        }

        Gap(24)

        LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            item {
                var foundPatient by remember { mutableStateOf(false) }

                Step(
                    index = 1,
                    title = "Find the patient",
                    description = "Find the patient",
                    disabled = foundPatient,
                    onNext = {
                        markAsArrived()
                        foundPatient = true
                    }
                )
            }
            itemsIndexed(manual) { index, step ->
                Step(index = index + 2, title = step.title, description = step.description)
            }
        }
    }
}