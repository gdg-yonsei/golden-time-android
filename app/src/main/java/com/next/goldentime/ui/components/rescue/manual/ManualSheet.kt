package com.next.goldentime.ui.components.rescue.manual

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.next.goldentime.repository.case.Step
import com.next.goldentime.ui.components.common.ChipButton

typealias Manual = List<Step>

@Composable
fun ManualSheet(manual: Manual, showPatientID: () -> Unit, markAsArrived: () -> Unit) {
    Column(modifier = Modifier.padding(24.dp)) {
        Text(
            "Instructions",
            fontSize = 24.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFF201A18)
        )

        Row(horizontalArrangement = Arrangement.spacedBy(7.dp)) {
            ChipButton(label = "Call 911", icon = Icons.Filled.Call, noBorder = true) {
                Log.d("GOLDEN TIME", "CALL to 911")
            }
            ChipButton(
                label = "Medical ID",
                icon = Icons.Filled.LocalHospital,
                noBorder = true
            ) {
                showPatientID()
            }
        }

        Spacer(Modifier.height(24.dp))

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