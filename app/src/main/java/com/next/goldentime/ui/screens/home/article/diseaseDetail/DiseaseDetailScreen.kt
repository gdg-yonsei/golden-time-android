package com.next.goldentime.ui.screens.home.article.diseaseDetail

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.next.goldentime.ui.components.common.FragmentTab
import com.next.goldentime.ui.components.common.Suspender
import com.next.goldentime.ui.components.common.TopBar
import com.next.goldentime.ui.components.common.TopBarIcon
import com.next.goldentime.ui.components.home.WithTopBar
import com.next.goldentime.ui.components.home.article.MarkdownFragment
import com.next.goldentime.ui.components.home.article.diseaseDetail.CaseListFragment

@Composable
fun DiseaseDetailScreen(
    diseaseId: Int,
    navigateBack: () -> Unit,
    model: DiseaseDetailViewModel = viewModel(factory = DiseaseDetailViewModelFactory(diseaseId))
) {
    val disease by model.disease.observeAsState()

    val tabs = listOf("Description", "Cases")

    /**
     * Content
     */
    WithTopBar(topBar = {
        TopBar("", left = TopBarIcon(Icons.Outlined.ArrowBack) { navigateBack() })
    }) {
        Suspender(disease) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Header
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(209.dp)
                        .padding(24.dp),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Text(it.title, fontSize = 32.sp, color = Color(0xFF201A18))
                }

                FragmentTab(tabs = tabs) { index ->
                    when (index) {
                        0 -> MarkdownFragment(it.description)
                        1 -> CaseListFragment(it.cases)
                    }
                }
            }
        }
    }
}