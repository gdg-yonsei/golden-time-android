package com.next.goldentime.ui.components.common.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun TextButton(label: String, important: Boolean = false, onClick: () -> Unit) {
    androidx.compose.material3.TextButton(
        onClick = onClick,
        contentPadding = PaddingValues(12.dp, 10.dp)
    ) {
        Text(
            label,
            style = MaterialTheme.typography.bodyLarge,
            color = if (important) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.onSurface
        )
    }
}