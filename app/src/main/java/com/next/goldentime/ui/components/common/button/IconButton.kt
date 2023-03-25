package com.next.goldentime.ui.components.common.button

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun IconButton(
    icon: ImageVector,
    size: Int = 48,
    onClick: () -> Unit
) {
    androidx.compose.material3.IconButton(modifier = Modifier.size(size.dp), onClick = onClick) {
        Icon(imageVector = icon, contentDescription = null)
    }
}