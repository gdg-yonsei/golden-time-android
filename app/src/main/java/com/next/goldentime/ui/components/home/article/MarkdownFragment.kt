package com.next.goldentime.ui.components.home.article

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MarkdownFragment(raw: String) {
    Column(modifier = Modifier.padding(24.dp)) {
        Text(raw)
    }
}