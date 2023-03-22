package com.next.goldentime.ui.screens.home.article.caseDetail

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
fun CaseDetailScreen(
    caseId: Int,
    navigateBack: () -> Unit,
    model: CaseDetailViewModel = viewModel(factory = CaseDetailViewModelFactory(caseId))
) {
    val case by model.case.observeAsState()

    /**
     * Content
     */
    WithTopBar(topBar = {
        Box(
            modifier = Modifier
                .height(228.dp)
                .padding(24.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Text(case?.title ?: "Loading", fontSize = 32.sp)
        }
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            case?.let {
                Text(it.subtitle)
                Text(it.overview)
                Text("${it.symptoms.size}")
                Text(it.causes)
                Text("${it.manual.size}")
            }
        }
    }
}