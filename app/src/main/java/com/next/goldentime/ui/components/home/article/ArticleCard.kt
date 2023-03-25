package com.next.goldentime.ui.components.home.article

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleCard(
    title: String,
    description: String,
    icon: ImageVector,
    behavior: (@Composable () -> Unit)? = null,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary.copy(.05f)
        ),
        shape = RoundedCornerShape(24.dp),
        onClick = onClick
    ) {
        Column(modifier = Modifier.padding(28.dp)) {
            ArticleItem(title = title, description = description, icon = icon, behavior = behavior)
        }
    }
}