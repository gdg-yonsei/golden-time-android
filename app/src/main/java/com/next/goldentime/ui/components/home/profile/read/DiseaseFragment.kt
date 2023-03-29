package com.next.goldentime.ui.components.home.profile.read

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.HeartBroken
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.next.goldentime.ui.components.common.Progress
import com.next.goldentime.ui.components.common.Validator
import com.next.goldentime.ui.components.common.layout.Fill
import com.next.goldentime.ui.components.common.layout.Suspender
import com.next.goldentime.ui.components.home.article.ArticleCard
import com.next.goldentime.ui.screens.home.profile.read.ProfileReadViewModel

@Composable
fun DiseaseFragment(model: ProfileReadViewModel) {
    val diseases by model.diseases.observeAsState()

    Suspender(diseases, { Fill { Progress() } }) {
        val hasDisease = it.isNotEmpty()

        Validator(valid = hasDisease, invalidContent = { DiseaseAddGuide() }) {
            val scrollState = rememberScrollState()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                it.map { disease ->
                    ArticleCard(
                        title = disease.title,
                        description = disease.subtitle,
                        icon = Icons.Outlined.HeartBroken,
                        behavior = {}
                    ) {}
                }
            }
        }
    }
}