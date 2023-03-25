package com.next.goldentime.ui.components.common.button

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Emergency
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ChipButton(label: String, icon: ImageVector, onClick: () -> Unit) {
    AssistChip(
        label = { Text(label) },
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
        },
        colors = AssistChipDefaults.assistChipColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            leadingIconContentColor = Color.Black
        ),
        shape = RoundedCornerShape(50),
        border = null,
        onClick = onClick
    )
}

@Preview
@Composable
private fun ChipButtonPreview1() {
    ChipButton(label = "Label", icon = Icons.Filled.Emergency) {}
}
