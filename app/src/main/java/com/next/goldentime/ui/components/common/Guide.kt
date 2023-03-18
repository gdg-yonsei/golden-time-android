package com.next.goldentime.ui.components.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Guide(title: String, description: String? = null) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            title,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            fontWeight = FontWeight(700),
            color = Color(0xFF201A18)
        )
        description?.let {
            Text(
                description,
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF201A18)
            )
        }
    }
}

@Preview
@Composable
private fun GuidePreview1() {
    Guide(title = "Title", description = "Description\nwith new line")
}