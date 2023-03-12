package com.next.goldentime.ui.screens.home.article.diseaseDetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.next.goldentime.ui.components.common.TopBar
import com.next.goldentime.ui.components.common.TopBarIcon
import com.next.goldentime.ui.components.home.WithTopBar

@Composable
fun DiseaseDetailScreen(
    diseaseId: Int,
    navigateBack: () -> Unit,
    model: DiseaseDetailViewModel = viewModel(factory = DiseaseDetailViewModelFactory(diseaseId))
) {
    val disease by model.disease.observeAsState()

    WithTopBar(topBar = {
        TopBar(
            disease?.title ?: "Loading",
            left = TopBarIcon(Icons.Outlined.ArrowBack) { navigateBack() }
        )
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            disease?.let {
                Text(it.subtitle)
                Text(it.overview)
                Text("${it.manual.size}")
            }
        }
    }
}