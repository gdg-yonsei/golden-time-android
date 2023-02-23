package com.next.goldentime.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.ui.graphics.vector.ImageVector

sealed class MainNavigation(
    val route: String,
    val label: String,
    val icon: ImageVector,
) {
    object Profile : MainNavigation("profile", "Profile", Icons.Filled.AccountCircle)
    object SOS : MainNavigation("sos", "SOS", Icons.Filled.Notifications)
    object Article : MainNavigation("article", "Article", Icons.Filled.Article)
}