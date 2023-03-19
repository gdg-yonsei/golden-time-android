package com.next.goldentime.ui.components.home.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.next.goldentime.ui.components.common.Suspender
import com.next.goldentime.ui.components.common.Validator
import com.next.goldentime.ui.screens.home.profile.ProfileViewModel

@Composable
fun DiseaseFragment(model: ProfileViewModel) {
    val diseases by model.diseases.observeAsState()

    Suspender(data = diseases) {
        val hasDisease = it.isNotEmpty()

        Validator(valid = hasDisease, invalidContent = { DiseaseAddGuide() }) {
            val scrollState = rememberScrollState()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
            ) {
                Text("Disease List $it")
            }
        }
    }
}