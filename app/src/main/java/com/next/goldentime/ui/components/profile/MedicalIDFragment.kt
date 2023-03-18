package com.next.goldentime.ui.components.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.next.goldentime.ui.components.common.Suspender
import com.next.goldentime.ui.components.common.Validator
import com.next.goldentime.ui.screens.home.profile.ProfileViewModel

@Composable
fun MedicalIDFragment(model: ProfileViewModel) {
    val user by model.user.observeAsState()

    Suspender(data = user) {
        val medicalIDValid = model.checkMedicalIDValid(it)

        Validator(valid = medicalIDValid, invalidContent = { MedicalIDEditGuide() }) {
            val scrollState = rememberScrollState()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
            ) {
                TextField(
                    label = { Text("Name") },
                    placeholder = { Text("Not set") },
                    value = it.name,
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = true
                )
                TextField(
                    label = { Text("Birth date") },
                    placeholder = { Text("MM/DD/YYYY") },
                    value = it.birthDate,
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = true
                )
                TextField(
                    label = { Text("Height") },
                    placeholder = { Text("Not set") },
                    value = it.height.toString(),
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = true
                )
                TextField(
                    label = { Text("Weight") },
                    placeholder = { Text("Not set") },
                    value = it.weight.toString(),
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = true
                )
                TextField(
                    label = { Text("Blood type") },
                    placeholder = { Text("Not set") },
                    value = it.bloodType,
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = true,
                )
                TextField(
                    label = { Text("Allergies") },
                    placeholder = { Text("Not set") },
                    value = it.allergies,
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = true,
                )
                TextField(
                    label = { Text("Medications") },
                    placeholder = { Text("Not set") },
                    value = it.medications,
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = true,
                )
                TextField(
                    label = { Text("Medical Notes") },
                    placeholder = { Text("Not set") },
                    value = it.medicalNotes,
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = true,
                )
            }
        }

    }
}