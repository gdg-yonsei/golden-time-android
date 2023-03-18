package com.next.goldentime.ui.components.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun <T> Suspender(data: T?, content: @Composable (data: T) -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        data?.let { content(data) } ?: CircularProgressIndicator()
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