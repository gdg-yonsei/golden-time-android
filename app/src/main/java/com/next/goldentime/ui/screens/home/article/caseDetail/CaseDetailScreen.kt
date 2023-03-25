package com.next.goldentime.ui.screens.home.article.caseDetail

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
import com.next.goldentime.ui.components.common.Progress
import com.next.goldentime.ui.components.common.TopBar
import com.next.goldentime.ui.components.common.TopBarIcon
import com.next.goldentime.ui.components.common.layout.Fill
import com.next.goldentime.ui.components.common.layout.Suspender
import com.next.goldentime.ui.components.home.WithTopBar
import com.next.goldentime.ui.components.home.article.MarkdownFragment
import com.next.goldentime.ui.components.home.article.caseDetail.ManualFragment
import com.next.goldentime.ui.components.home.article.caseDetail.SymptomListFragment

@Composable
fun CaseDetailScreen(
    caseId: Int,
    navigateBack: () -> Unit,
    model: CaseDetailViewModel = viewModel(factory = CaseDetailViewModelFactory(caseId))
) {
    val case by model.case.observeAsState()

    val tabs = listOf("Overview", "Symptoms", "Causes and Preventions", "Manual")
    /**
     * Content
     */
    WithTopBar(topBar = {
        TopBar("", left = TopBarIcon(Icons.Outlined.ArrowBack) { navigateBack() })
    }) {
        Suspender(case, { Fill { Progress() } }) {
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

                // Contents
                FragmentTab(tabs = tabs, scrollable = true) { index ->
                    when (index) {
                        0 -> MarkdownFragment(it.overview)
                        1 -> SymptomListFragment(it.symptoms)
                        2 -> MarkdownFragment(it.causes)
                        3 -> ManualFragment(it.manual)
                    }
                }
            }
        }
    }
}