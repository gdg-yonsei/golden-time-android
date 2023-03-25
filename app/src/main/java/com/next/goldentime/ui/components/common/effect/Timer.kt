package com.next.goldentime.ui.components.common.effect

import androidx.compose.runtime.*
import kotlinx.coroutines.delay

@Composable
fun Timer(
    duration: Int,
    onComplete: () -> Unit,
    content: @Composable (remainingTime: Int) -> Unit,
) {
    var remainingTime by remember { mutableStateOf(duration) }

    LaunchedEffect(Unit) {
        while (remainingTime > 0) {
            delay(1000)
            remainingTime--
        }

        onComplete()
    }

    content(remainingTime = remainingTime)
}