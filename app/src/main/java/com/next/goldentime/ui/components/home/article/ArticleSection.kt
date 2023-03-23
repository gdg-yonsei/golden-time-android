package com.next.goldentime.ui.components.home.article

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ArticleSection(title: String, vararg items: ArticleItemData) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0x119B4511)),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(modifier = Modifier.padding(0.dp, 36.dp, 0.dp, 12.dp)) {
            // Title
            Text(
                title,
                modifier = Modifier.padding(28.dp, 0.dp),
                fontSize = 22.sp,
                color = Color(0xFF9B4511)
            )

            Spacer(Modifier.height(2.dp))

            // Items
            items.mapIndexed { index, item ->
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Transparent,
                    onClick = { item.onClick() }
                ) {
                    Column(modifier = Modifier.padding(28.dp, 24.dp)) {
                        ArticleItem(
                            title = item.title,
                            description = item.description,
                            icon = item.icon
                        )
                    }
                }

                if (index < items.size - 1)
                    Divider(
                        modifier = Modifier
                            .height(.5.dp)
                            .padding(28.dp, 0.dp),
                        color = Color(0xFFD7C2B9)
                    )
            }
        }
    }
}

data class ArticleItemData(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val onClick: () -> Unit
)