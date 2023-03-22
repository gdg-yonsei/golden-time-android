package com.next.goldentime.ui.components.sos.state

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
fun NoRescuerFragment() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.image_location),
            contentDescription = null,
            modifier = Modifier.size(216.dp),
        )
        Spacer(Modifier.height(40.dp))
        Guide(description = "Users around you will receive an\nemergency SOS message.")
    }
}