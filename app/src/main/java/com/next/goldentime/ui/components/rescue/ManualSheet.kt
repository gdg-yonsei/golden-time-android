package com.next.goldentime.ui.components.rescue

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.next.goldentime.ui.components.common.ChipButton

@Composable
fun ManualSheet(showPatientID: () -> Unit) {
    Column(modifier = Modifier.padding(24.dp)) {
        Text(
            "Instructions",
            fontSize = 24.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFF201A18)
        )

        Row(horizontalArrangement = Arrangement.spacedBy(7.dp)) {
            ChipButton(label = "Call 911", icon = Icons.Filled.Call, noBorder = true) {

            }
            ChipButton(
                label = "Medical ID",
                icon = Icons.Filled.LocalHospital,
                noBorder = true
            ) {
                showPatientID()
            }
        }

        Text("Step1")
        Text("Step2")
        Text("Step3")
        Text("Step4")
        Text("Step5")
        Text("Step6")
    }
}