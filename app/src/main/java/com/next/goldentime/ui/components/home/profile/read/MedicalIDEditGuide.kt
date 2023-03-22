package com.next.goldentime.ui.components.home.profile.read

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.next.goldentime.R
import com.next.goldentime.ui.components.common.Guide

@Composable
fun MedicalIDEditGuide() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.image_empty),
            contentDescription = null,
            modifier = Modifier.size(192.dp),
        )
        Spacer(Modifier.height(42.dp))
        Guide(
            title = "No Data",
            description = "Tap edit button in the upper-right\ncorner to set up your Medical ID."
        )
    }
}