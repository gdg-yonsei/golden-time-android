package com.next.goldentime.ui.components.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.next.goldentime.ui.components.common.layout.Fill

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
            ScrollableTabRow(selectedTabIndex = currentTabIndex, divider = {}) {
                tabRow()
            }
            Divider(color = MaterialTheme.colorScheme.surfaceVariant)
        } else {
            TabRow(selectedTabIndex = currentTabIndex, divider = {}) {
                tabRow()
            }
            Divider(color = MaterialTheme.colorScheme.surfaceVariant)
        }

        // Content
        Fill { content(currentTabIndex) }
    }
}
