package com.next.goldentime.ui.components.sos.detect

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.next.goldentime.R
import com.next.goldentime.ui.components.common.layout.Gap
import com.next.goldentime.ui.components.common.text.Guide
import com.next.goldentime.ui.components.common.text.Highlight

@Composable
fun FallDetectGuide() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Highlight("It looks like you’ve\ntaken a hard fall")
        Gap(72)
        Image(
            painter = painterResource(id = R.drawable.image_falling),
            contentDescription = null,
            modifier = Modifier
                .width(209.dp)
                .height(240.dp),
        )
        Gap(40)
        Guide("Do you need help? We will trigger\nEmergency SOS if you don’t respond.")
    }
}