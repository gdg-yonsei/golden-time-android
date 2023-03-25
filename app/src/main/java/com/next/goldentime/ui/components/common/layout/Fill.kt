package com.next.goldentime.ui.components.common.layout

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun Fill(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        content()
    }
}

@Composable
fun ColumnScope.Fill(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
        contentAlignment = Alignment.Center,
    ) {
        content()
    }
}

@Composable
fun RowScope.Fill(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .weight(1f),
        contentAlignment = Alignment.Center,
    ) {
        content()
    }
}