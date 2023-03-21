package com.next.goldentime.ui.screens.home.article.diseaseList

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.next.goldentime.ui.components.home.article.ArticleCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiseaseListScreen(
    navigateBack: () -> Unit,
    showDetail: (diseaseId: Int) -> Unit,
    model: DiseaseListViewModel = viewModel()
) {
    val diseases by model.diseases.observeAsState()

    WithTopBar(topBar = {
        TopBar(
            "Disease",
            left = TopBarIcon(Icons.Outlined.ArrowBack) { navigateBack() }
        )
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            diseases?.map { disease ->
                ArticleCard(title = disease.title, description = disease.subtitle) {
                    showDetail(disease.id)
                }
            }
        }
    }
}