package com.next.goldentime.ui.components.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.next.goldentime.ui.screens.home.profile.ProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicalIDFragment(model: ProfileViewModel) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        var name by remember { mutableStateOf("") }
        var birthDate by remember { mutableStateOf("") }
        var height by remember { mutableStateOf("") }
        var weight by remember { mutableStateOf("") }
        var bloodType by remember { mutableStateOf("") }
        var allergies by remember { mutableStateOf("") }
        var medications by remember { mutableStateOf("") }
        var medicalNotes by remember { mutableStateOf("") }

        TextField(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Name") },
            readOnly = true
        )
        TextField(
            value = birthDate,
            onValueChange = { birthDate = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Birth date") },
            readOnly = true
        )
        TextField(
            value = height,
            onValueChange = { height = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Height") },
            readOnly = true
        )
        TextField(
            value = weight,
            onValueChange = { weight = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Weight") },
            readOnly = true
        )
        TextField(
            value = bloodType,
            onValueChange = { bloodType = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Blood type") },
            readOnly = true
        )
        TextField(
            value = allergies,
            onValueChange = { allergies = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Allergies") },
            readOnly = true
        )
        TextField(
            value = medications,
            onValueChange = { medications = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Medications") },
            readOnly = true
        )
        TextField(
            value = medicalNotes,
            onValueChange = { medicalNotes = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Medical Notes") },
            readOnly = true
        )
    }
}