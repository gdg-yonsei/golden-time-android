package com.next.goldentime.ui.screens.home.article.list

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.next.goldentime.ui.components.common.TopBar
import com.next.goldentime.ui.components.home.WithTopBar

@Composable
fun ArticleListScreen(showDiseaseList: () -> Unit) {
    WithTopBar(topBar = { TopBar("Articles") }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("What will you learn today?")
            Spacer(Modifier.height(24.dp))
            ElevatedButton(onClick = { showDiseaseList() }) {
                Text("Show diseases")
            }
        }
    }
}