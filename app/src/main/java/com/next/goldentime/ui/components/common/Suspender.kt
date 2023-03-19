package com.next.goldentime.ui.components.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun <T> Suspender(data: T?, content: @Composable (data: T) -> Unit) {
    data?.let { content(data) }
        ?: Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
}

@Preview(showBackground = true)
@Composable
fun SuspenderPreview1() {
    Suspender(data = null) {
        Text("This text should not be shown.")
    }
}

@Preview(showBackground = true)
@Composable
fun SuspenderPreview2() {
    Suspender(data = "Hello") {
        Text("This text should be shown with $it")
    }
}