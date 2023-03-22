package com.next.goldentime.ui.components.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.next.goldentime.usecase.profile.MedicalID

@Composable
fun MedicalIDReader(medicalID: MedicalID) {
    val medicalIDFields = listOf(
        Field("Name", "Not set", medicalID.name),
        Field("Birth date", "Not set", medicalID.birthDate),
        Field("Height", "Not set", medicalID.height.toString()),
        Field("Weight", "Not set", medicalID.weight.toString()),
        Field("Blood type", "Not set", medicalID.bloodType),
        Field("Allergies", "Not set", medicalID.allergies),
        Field("Medications", "Not set", medicalID.medications),
        Field("Medical Notes", "Not set", medicalID.medicalNotes),
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        contentPadding = PaddingValues(24.dp)
    ) {
        items(medicalIDFields) {
            TextField(
                label = { Text(it.label) },
                placeholder = { Text(it.placeholder) },
                value = it.value,
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                readOnly = true
            )
        }
    }
}

private data class Field(val label: String, val placeholder: String, val value: String)