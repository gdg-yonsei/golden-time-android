package com.next.goldentime.ui.screens.home.article.diseaseDetail

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.next.goldentime.ui.components.home.WithTopBar

@Composable
fun DiseaseDetailScreen(
    diseaseId: Int,
    navigateBack: () -> Unit,
    model: DiseaseDetailViewModel = viewModel(factory = DiseaseDetailViewModelFactory(diseaseId))
) {
    val disease by model.disease.observeAsState()

    WithTopBar(topBar = {
        Box(
            modifier = Modifier
                .height(228.dp)
                .padding(24.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Text(disease?.title ?: "Loading", fontSize = 32.sp)
        }
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