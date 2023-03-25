package com.next.goldentime.ui.components.common.effect

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable

@Composable
fun PreventBack() {
    BackHandler(true) {}
}