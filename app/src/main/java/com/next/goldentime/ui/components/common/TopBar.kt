package com.next.goldentime.ui.components.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

data class TopBarIcon(val icon: ImageVector, val onClick: () -> Unit)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    left: TopBarIcon? = null,
    right: TopBarIcon? = null,
) {
    CenterAlignedTopAppBar(
        title = { Text(title, fontSize = 22.sp, fontWeight = FontWeight(700), color = Color(0xff201A18)) },
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