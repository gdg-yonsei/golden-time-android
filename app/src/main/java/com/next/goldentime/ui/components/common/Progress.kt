package com.next.goldentime.ui.components.common

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

const val MIN_SIZE = 40f
const val MAX_SIZE = 150f
const val DURATION = 700

@Composable
fun Progress() {
    val infiniteTransition = rememberInfiniteTransition()

    val size by infiniteTransition.animateFloat(
        initialValue = MIN_SIZE,
        targetValue = MAX_SIZE,
        animationSpec = infiniteRepeatable(
            animation = tween(DURATION, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )
    val alpha by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(DURATION, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Box(contentAlignment = Alignment.Center) {
        Surface(
            modifier = Modifier
                .size(size.dp)
                .alpha(alpha),
            shape = CircleShape,
            color = MaterialTheme.colorScheme.tertiary.copy(.3f)
        ) {}
        Surface(
            modifier = Modifier.size(MIN_SIZE.dp),
            shape = CircleShape,
            color = MaterialTheme.colorScheme.tertiary
        ) {}
    }
}

@Preview
@Composable
private fun ProgressPreview1() {
    Progress()
}