package com.next.goldentime.ui.components.common.layout

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun <T> Suspender(
    data: T?,
    loadingContent: @Composable () -> Unit,
    content: @Composable (data: T) -> Unit
) {
    data?.let { content(data) } ?: loadingContent()
}

@Preview(showBackground = true)
@Composable
fun SuspenderPreview1() {
    Suspender(data = null, loadingContent = { CircularProgressIndicator() }) {
        Text("This text should not be shown.")
    }
}

@Preview(showBackground = true)
@Composable
fun SuspenderPreview2() {
    Suspender(data = "Hello", loadingContent = {}) {
        Text("This text should be shown with $it")
    }
}