package com.next.goldentime.ui.components.home.profile.read

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.next.goldentime.R
import com.next.goldentime.ui.components.common.layout.Gap
import com.next.goldentime.ui.components.common.text.Guide

@Composable
fun DiseaseAddGuide() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.image_empty),
            contentDescription = null,
            modifier = Modifier.size(200.dp),
        )
        Gap(56)
        Guide("No Data", important = true)
        Gap(8)
        Guide("Explore “Articles” page to register\nyour Medical Conditions.")
    }
}