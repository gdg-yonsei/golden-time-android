package com.next.goldentime.ui.components.rescue.manual

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.next.goldentime.ui.components.common.YoutubePlayer
import com.next.goldentime.ui.components.common.layout.Gap
import com.next.goldentime.ui.theme.Neutral50
import com.next.goldentime.ui.theme.Neutral80
import com.next.goldentime.ui.theme.Tertiary60

@Composable
fun Step(
    index: Int,
    title: String,
    description: String,
    videoUrl: String? = null,
    disabled: Boolean = false,
    onNext: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Column(
            modifier = Modifier.heightIn(100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(
                        color = if (disabled) Neutral80 else Tertiary60,
                        shape = RoundedCornerShape(50)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text("$index", style = MaterialTheme.typography.labelLarge, color = Color.White)
            }
            Gap(8)
            Divider(
                color = Neutral80,
                modifier = Modifier
                    .width(1.dp)
                    .weight(1f)
            )
        }

        Column {
            Text(
                title,
                style = MaterialTheme.typography.bodyLarge,
                color = if (disabled) Neutral80 else Color(0xFF333333)
            )
            if (!disabled) {
                Gap(2)
                Text(
                    description,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Neutral50
                )
                videoUrl?.let { videoUrl ->
                    Gap(12)
                    YoutubePlayer(videoUrl)
                }
                onNext?.let { onNext ->
                    Gap(12)
                    ElevatedButton(onClick = { onNext() }) { Text("Next") }
                }
            }
        }
    }
}