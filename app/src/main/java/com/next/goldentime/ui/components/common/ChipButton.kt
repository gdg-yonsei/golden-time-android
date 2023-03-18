package com.next.goldentime.ui.components.common

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Emergency
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
            containerColor = Color(0xFFFFDAD9),
            leadingIconContentColor = Color(0xFF201A18)
        ),
        shape = RoundedCornerShape(50),
        border = AssistChipDefaults.assistChipBorder(borderColor = Color(0xFF201A18)),
        onClick = onClick
    )
}

@Preview
@Composable
private fun ChipButtonPreview1() {
    ChipButton(label = "Label", icon = Icons.Filled.Emergency) {}
}