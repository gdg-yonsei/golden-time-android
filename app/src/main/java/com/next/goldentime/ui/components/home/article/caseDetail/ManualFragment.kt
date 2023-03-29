package com.next.goldentime.ui.components.home.article.caseDetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.next.goldentime.repository.case.Step
import com.next.goldentime.ui.components.common.YoutubePlayer
import com.next.goldentime.ui.components.rescue.manual.Step

typealias Manual = List<Step>

@Composable
fun ManualFragment(manual: Manual) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        manual.mapIndexed { index, step ->
            Step(
                index = index + 1,
                title = step.title,
                description = step.description,
                videoUrl = step.videoUrl,
            )
        }
    }
}