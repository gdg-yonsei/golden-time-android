package com.next.goldentime.ui.components.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun FragmentTab(
    tabs: List<String>,
    scrollable: Boolean = false,
    content: @Composable (currentTabIndex: Int) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        var currentTabIndex by remember { mutableStateOf(0) }

        // Tab
        @Composable
        fun tabRow() {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    selected = index == currentTabIndex,
                    onClick = { currentTabIndex = index },
                    text = { Text(tab) }
                )
            }
        }

        if (scrollable) {
            ScrollableTabRow(selectedTabIndex = currentTabIndex) { tabRow() }
        } else {
            TabRow(selectedTabIndex = currentTabIndex) { tabRow() }
        }

        // Content
        Box(modifier = Modifier.weight(1f)) {
            content(currentTabIndex)
        }
    }
}
