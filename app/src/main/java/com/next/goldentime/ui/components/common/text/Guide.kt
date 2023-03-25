package com.next.goldentime.ui.components.common.text

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Guide(text: String, important: Boolean = false) {
    Text(
        text,
        style = if (important) MaterialTheme.typography.titleLarge else MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurface,
        textAlign = TextAlign.Center
    )
}

@Preview
@Composable
private fun GuidePreview1() {
    Guide("guidance")
}

@Preview
@Composable
private fun GuidePreview2() {
    Guide("guidance", important = true)
}