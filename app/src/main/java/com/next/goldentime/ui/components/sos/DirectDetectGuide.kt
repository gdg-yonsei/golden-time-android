package com.next.goldentime.ui.components.sos

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.next.goldentime.R
import com.next.goldentime.ui.components.common.Guide

@Composable
fun DirectDetectGuide() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.image_falling),
            contentDescription = null,
            modifier = Modifier
                .width(209.dp)
                .height(240.dp),
        )
        Spacer(Modifier.height(28.dp))
        Guide(
            title = "It looks like you’ve taken a hard fall",
            description = "Do you need help? We will trigger\nEmergency SOS if you don’t respond."
        )
    }
}