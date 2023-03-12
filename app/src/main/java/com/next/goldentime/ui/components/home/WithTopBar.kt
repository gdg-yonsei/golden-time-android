package com.next.goldentime.ui.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.next.goldentime.ui.components.common.TopBar

@Composable
fun WithTopBar(topBar: @Composable () -> Unit, content: @Composable () -> Unit) {
    Column {
        topBar()
        Box(modifier = Modifier.weight(1f)) {
            content()
        }
    }
}

@Preview(device = Devices.PHONE)
@Composable
fun WithTopBarPreview1() {
    WithTopBar(topBar = { TopBar("Title") }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Hello")
        }
    }
}