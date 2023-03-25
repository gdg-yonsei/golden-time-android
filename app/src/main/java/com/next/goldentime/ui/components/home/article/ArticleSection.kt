package com.next.goldentime.ui.components.home.article

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.next.goldentime.ui.components.common.layout.Gap
import com.next.goldentime.ui.theme.NeutralVariant80

@Composable
fun ArticleSection(title: String, vararg items: ArticleItemData) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary.copy(.05f)
        ),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(modifier = Modifier.padding(0.dp, 36.dp, 0.dp, 12.dp)) {
            // Title
            Text(
                title,
                modifier = Modifier.padding(28.dp, 0.dp),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Gap(2)

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
                        color = NeutralVariant80
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