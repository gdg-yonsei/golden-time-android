package com.next.goldentime.ui.components.home.sos

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.next.goldentime.R
import com.next.goldentime.ui.theme.Neutral99
import kotlinx.coroutines.delay

const val BUTTON_HEIGHT_WHEN_INACTIVE = 200
const val BUTTON_HEIGHT_WHEN_ACTIVE = 300

const val WHITE_FRACTION_WHEN_INACTIVE = 1f
const val WHITE_FRACTION_DECREASE_OFFSET = 0.01f

const val ICON_ALPHA_WHEN_INACTIVE = 1f
const val ICON_ALPHA_WHEN_ACTIVE = 0f

val gradient = Brush.linearGradient(listOf(Color(0xffFFB4AB), Color(0xff9C4145)))

@Composable
fun SOSTrigger(triggerSOS: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()

    var buttonHeight by remember { mutableStateOf(BUTTON_HEIGHT_WHEN_INACTIVE) }
    var whiteFraction by remember { mutableStateOf(WHITE_FRACTION_WHEN_INACTIVE) }
    var iconAlpha by remember { mutableStateOf(ICON_ALPHA_WHEN_INACTIVE) }

    val animatedButtonHeight by animateDpAsState(buttonHeight.dp)
    val animatedWhiteFraction by animateFloatAsState(whiteFraction)
    val animatedIconAlpha by animateFloatAsState(iconAlpha)

    LaunchedEffect(pressed) {
        while (pressed) {
            buttonHeight = BUTTON_HEIGHT_WHEN_ACTIVE
            whiteFraction -= WHITE_FRACTION_DECREASE_OFFSET
            iconAlpha = ICON_ALPHA_WHEN_ACTIVE

            if (whiteFraction < 0f) {
                triggerSOS()
                break
            }

            delay(30)
        }

        buttonHeight = BUTTON_HEIGHT_WHEN_INACTIVE
        whiteFraction = WHITE_FRACTION_WHEN_INACTIVE
        iconAlpha = ICON_ALPHA_WHEN_INACTIVE
    }

    Box(
        modifier = Modifier
            .width(200.dp)
            .height(animatedButtonHeight)
            .border(BorderStroke(2.5.dp, gradient), RoundedCornerShape(50))
            .clip(RoundedCornerShape(50))
            .background(gradient)
            .clickable(interactionSource = interactionSource, indication = null) {},
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(animatedWhiteFraction)
                .align(Alignment.TopCenter)
                .background(Neutral99)
        )
        Image(
            painter = painterResource(id = R.drawable.icon_sos),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .alpha(animatedIconAlpha),
        )
    }
}