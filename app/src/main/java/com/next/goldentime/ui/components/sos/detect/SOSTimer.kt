package com.next.goldentime.ui.components.sos.detect

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.next.goldentime.ui.components.common.Guide

@Composable
fun SOSTimer(remainingTime: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedButton(
            enabled = false,
            onClick = {},
            modifier = Modifier.size(200.dp),
            border = BorderStroke(
                2.5.dp,
                Brush.verticalGradient(listOf(Color(0xffFFB4AB), Color(0xff9C4145)))
            ),
        ) {
            Text(
                remainingTime.toString(),
                fontSize = 32.sp,
                fontWeight = FontWeight(400),
                color = Color(0xff9C4145)
            )
        }
        Spacer(Modifier.height(40.dp))
        Guide(title = "Auto notifications will be sent\nat the end of countdown")
    }
}