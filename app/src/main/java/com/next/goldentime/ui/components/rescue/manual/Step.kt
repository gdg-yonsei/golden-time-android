package com.next.goldentime.ui.components.rescue.manual

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Step(
    index: Int,
    title: String,
    description: String,
    disabled: Boolean = false,
    onNext: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(
                        color = Color(if (disabled) 0xFFD0C4C0 else 0xFFDB7174),
                        shape = RoundedCornerShape(50)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text("$index", fontSize = 16.sp, color = Color.White, textAlign = TextAlign.Center)
            }
            Spacer(Modifier.height(8.dp))
            Divider(
                color = Color(0xFFD0C4C0),
                modifier = Modifier
                    .defaultMinSize(1.dp, 61.dp)
                    .width(1.dp)
                    .weight(1f)
            )
        }

        Column {
            Text(
                title,
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                color = Color(if (disabled) 0xFFD0C4C0 else 0xFF333333)
            )
            if (!disabled) {
                Spacer(Modifier.height(2.dp))
                Text(
                    description,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF7F7571)
                )
                onNext?.let { onNext ->
                    Spacer(Modifier.height(16.dp))
                    ElevatedButton(onClick = { onNext() }) { Text("Next") }
                }
            }
        }
    }
}