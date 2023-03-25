package com.next.goldentime.ui.components.common.layout

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ColumnScope.Gap(space: Int) {
    Spacer(Modifier.height(space.dp))
}

@Composable
fun RowScope.Gap(space: Int) {
    Spacer(Modifier.width(space.dp))
}