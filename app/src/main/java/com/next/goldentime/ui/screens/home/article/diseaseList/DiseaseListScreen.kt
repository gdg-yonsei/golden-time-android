package com.next.goldentime.ui.screens.home.article.diseaseList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.next.goldentime.ui.components.common.Progress
import com.next.goldentime.ui.components.common.TopBar
import com.next.goldentime.ui.components.common.TopBarIcon
import com.next.goldentime.ui.components.common.layout.Fill
import com.next.goldentime.ui.components.common.layout.Suspender
import com.next.goldentime.ui.components.home.WithTopBar
import com.next.goldentime.ui.components.home.article.ArticleCard
import com.next.goldentime.ui.components.home.article.diseaseList.Bookmark

@Composable
fun DiseaseListScreen(
    navigateBack: () -> Unit,
    showDetail: (diseaseId: Int) -> Unit,
    model: DiseaseListViewModel = viewModel()
) {
    val diseases by model.diseases.observeAsState()
    val myDiseases by model.myDiseases.observeAsState()

    /**
     * Content
     */
    WithTopBar(topBar = {
        TopBar(
            "Diseases",
            left = TopBarIcon(Icons.Outlined.ArrowBack) { navigateBack() }
        )
    }) {
        Suspender(diseases, { Fill { Progress() } }) { diseases ->
            Suspender(myDiseases, { Fill { Progress() } }) { myDiseases ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(diseases) { disease ->
                        ArticleCard(
                            title = disease.title,
                            description = disease.subtitle,
                            icon = Icons.Outlined.HeartBroken,
                            behavior = {
                                Bookmark(
                                    disease = disease,
                                    myDiseases = myDiseases,
                                    setDiseases = model.setDiseases,
                                )
                            }
                        ) {
                            showDetail(disease.id)
                        }
                    }
                }
            }
        }
    }
}