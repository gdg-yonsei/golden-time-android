package com.next.goldentime.ui.components.sos.state

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.next.goldentime.R
import com.next.goldentime.ui.components.common.layout.Gap
import com.next.goldentime.ui.components.common.text.Guide
import com.next.goldentime.ui.components.home.sos.gradient

@Composable
fun RescuerStateFragment(rescuerNum: Int, closestRescuerDistance: Double) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            "Remaining\n${closestRescuerDistance}m",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.tertiary,
            textAlign = TextAlign.Center
        )
        Gap(20)
        Box(
            modifier = Modifier
                .size(200.dp)
                .border(BorderStroke(2.5.dp, gradient), RoundedCornerShape(50))
                .clip(RoundedCornerShape(50)),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_sos),
                contentDescription = null,
                modifier = Modifier.size(40.dp),
            )
        }
        Gap(40)
        AnimatedVisibility(visible = rescuerNum > 1) {
            Guide("There are ${rescuerNum - 1} more responders")
        }
    }
}