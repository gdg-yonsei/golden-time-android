package com.next.goldentime.ui.screens.home.article

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ArticleScreen(model: ArticleViewModel = viewModel()) {
    val diseases by model.getDiseases().observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(56.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("What are you interested in?")
        diseases?.map {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text("title : ${it.title}")
                    Text("subtitle : ${it.subtitle}")
                    Text("overview : ${it.overview}")
                    Text("manual : ${it.manual.size}")
                }
            }
        }
    }
}