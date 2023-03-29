package com.next.goldentime.ui.components.home.article.diseaseDetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.HeartBroken
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.next.goldentime.repository.case.SimpleCase
import com.next.goldentime.ui.components.home.article.ArticleCard

@Composable
fun CaseListFragment(cases: List<SimpleCase>, showCaseDetail: (caseId: Int) -> Unit) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        cases.map { case ->
            ArticleCard(
                title = case.title,
                description = case.subtitle,
                icon = Icons.Outlined.HeartBroken
            ) {
                showCaseDetail(case.id)
            }
        }
    }
}