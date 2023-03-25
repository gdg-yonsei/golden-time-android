package com.next.goldentime.ui.components.home.article

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.jeziellago.compose.markdowntext.MarkdownText

@Composable
fun MarkdownFragment(raw: String) {
    MarkdownText(markdown = raw, modifier = Modifier.padding(24.dp).fillMaxSize())
}