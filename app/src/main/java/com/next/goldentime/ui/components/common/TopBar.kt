package com.next.goldentime.ui.components.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

data class TopBarIcon(val icon: ImageVector, val onClick: () -> Unit)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    left: TopBarIcon? = null,
    right: TopBarIcon? = null,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                title,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        navigationIcon = {
            left?.let {
                IconButton(onClick = it.onClick) {
                    Icon(imageVector = it.icon, contentDescription = null)
                }
            }
        },
        actions = {
            right?.let {
                IconButton(onClick = it.onClick) {
                    Icon(imageVector = it.icon, contentDescription = null)
                }
            }
        }
    )
}

@Preview
@Composable
private fun TopBarPreview1() {
    TopBar("Title")
}

@Preview
@Composable
private fun TopBarPreview2() {
    TopBar(
        "Title",
        left = TopBarIcon(Icons.Default.ArrowBack) {},
    )
}

@Preview
@Composable
private fun TopBarPreview3() {
    TopBar(
        "Title",
        left = TopBarIcon(Icons.Default.ArrowBack) {},
        right = TopBarIcon(Icons.Default.Settings) {},
    )
}