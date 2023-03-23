package com.next.goldentime.ui.screens.home.article.diseaseList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.HeartBroken
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.next.goldentime.ui.components.common.Suspender
import com.next.goldentime.ui.components.common.TopBar
import com.next.goldentime.ui.components.common.TopBarIcon
import com.next.goldentime.ui.components.home.WithTopBar
import com.next.goldentime.ui.components.home.article.ArticleCard

@Composable
fun DiseaseListScreen(
    navigateBack: () -> Unit,
    showDetail: (diseaseId: Int) -> Unit,
    model: DiseaseListViewModel = viewModel()
) {
    val diseases by model.diseases.observeAsState()

    /**
     * Content
     */
    WithTopBar(topBar = {
        TopBar(
            "Diseases",
            left = TopBarIcon(Icons.Outlined.ArrowBack) { navigateBack() }
        )
    }) {
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Suspender(diseases) {
                it.map { disease ->
                    ArticleCard(
                        title = disease.title,
                        description = disease.subtitle,
                        icon = Icons.Outlined.HeartBroken
                    ) {
                        showDetail(disease.id)
                    }
                }
            }
        }
    }
}