package com.next.goldentime.ui.components.common.text

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Highlight(text: String) {
    Text(
        text,
        style = MaterialTheme.typography.headlineLarge,
        color = MaterialTheme.colorScheme.tertiary,
        textAlign = TextAlign.Center
    )
}

@Preview
@Composable
private fun HighlightPreview1() {
    Highlight("highlight")
}