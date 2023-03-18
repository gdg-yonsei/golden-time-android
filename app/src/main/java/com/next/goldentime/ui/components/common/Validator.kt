package com.next.goldentime.ui.components.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun Validator(
    valid: Boolean,
    invalidContent: @Composable () -> Unit,
    validContent: @Composable () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (valid) {
            validContent()
        } else {
            invalidContent()
        }
    }
}