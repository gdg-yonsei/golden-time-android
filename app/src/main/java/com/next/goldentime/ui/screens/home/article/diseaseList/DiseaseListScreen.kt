package com.next.goldentime.ui.screens.home.article.diseaseList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.HeartBroken
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.next.goldentime.ui.components.common.Suspender
import com.next.goldentime.ui.components.common.TopBar
import com.next.goldentime.ui.components.common.TopBarIcon
import com.next.goldentime.ui.components.home.WithTopBar
import com.next.goldentime.ui.components.home.article.ArticleCard
import kotlinx.coroutines.launch

@Composable
fun DiseaseListScreen(
    navigateBack: () -> Unit,
    showDetail: (diseaseId: Int) -> Unit,
    model: DiseaseListViewModel = viewModel()
) {
    val composeScope = rememberCoroutineScope()

    val diseases by model.diseases.observeAsState()
    val myDiseases by model.myDiseases.observeAsState()

    fun addMyDisease(id: Int) {
        composeScope.launch {
            model.setDiseases(myDiseases!!.plus(id))
        }
    }

    fun removeMyDisease(id: Int) {
        composeScope.launch {
            model.setDiseases(myDiseases!!.minus(id))
        }
    }

    /**
     * Content
     */
    WithTopBar(topBar = {
        TopBar(
            "Diseases",
            left = TopBarIcon(Icons.Outlined.ArrowBack) { navigateBack() }
        )
    }) {
        Suspender(diseases) {
            val scrollState = rememberScrollState()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                it.map { disease ->
                    ArticleCard(
                        title = disease.title,
                        description = disease.subtitle,
                        icon = Icons.Outlined.HeartBroken,
                        bookmark = {
                            when (myDiseases?.contains(disease.id) ?: false) {
                                true -> IconButton(onClick = { removeMyDisease(disease.id) }) {
                                    Icon(
                                        imageVector = Icons.Outlined.Bookmark,
                                        contentDescription = null
                                    )
                                }
                                false -> IconButton(onClick = { addMyDisease(disease.id) }) {
                                    Icon(
                                        imageVector = Icons.Outlined.BookmarkBorder,
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                    ) {
                        showDetail(disease.id)
                    }
                }
            }
        }
    }
}