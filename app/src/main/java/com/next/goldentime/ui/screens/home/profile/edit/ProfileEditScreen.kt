package com.next.goldentime.ui.screens.home.profile.edit

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.next.goldentime.ui.components.common.Progress
import com.next.goldentime.ui.components.common.TopBar
import com.next.goldentime.ui.components.common.TopBarIcon
import com.next.goldentime.ui.components.common.layout.Fill
import com.next.goldentime.ui.components.common.layout.Suspender
import com.next.goldentime.ui.components.home.WithTopBar
import kotlinx.coroutines.launch

@Composable
fun ProfileEditScreen(navigateBack: () -> Unit, model: ProfileEditViewModel = viewModel()) {
    val composeScope = rememberCoroutineScope()

    val medicalID by model.medicalID.observeAsState()

    fun setMedicalID(
        name: String,
        birthDate: String,
        height: Int,
        weight: Int,
        bloodType: String,
        allergies: String,
        medications: String,
        medicalNotes: String
    ) {
        composeScope.launch {
            model.setMedicalID(
                name,
                birthDate,
                height,
                weight,
                bloodType,
                allergies,
                medications,
                medicalNotes
            )

            navigateBack()
        }
    }

    /**
     * Content
     */
    Suspender(medicalID, { Fill { Progress() } }) {
        var name by remember { mutableStateOf(it.name) }
        var birthDate by remember { mutableStateOf(it.birthDate) }
        var height by remember { mutableStateOf(it.height) }
        var weight by remember { mutableStateOf(it.weight) }
        var bloodType by remember { mutableStateOf(it.bloodType) }
        var allergies by remember { mutableStateOf(it.allergies) }
        var medications by remember { mutableStateOf(it.medications) }
        var medicalNotes by remember { mutableStateOf(it.medicalNotes) }

        WithTopBar(topBar = {
            TopBar(
                "Medical ID",
                left = TopBarIcon(Icons.Outlined.ArrowBack) { navigateBack() },
                right = TopBarIcon(Icons.Outlined.Done) {
                    setMedicalID(
                        name,
                        birthDate,
                        height,
                        weight,
                        bloodType,
                        allergies,
                        medications,
                        medicalNotes
                    )
                },
            )
        }) {
            val scrollState = rememberScrollState()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp),
            ) {
                OutlinedTextField(
                    label = { Text("Name *") },
                    placeholder = { Text("Not set") },
                    value = name,
                    onValueChange = { name = it },
                    modifier = Modifier.fillMaxWidth(),
                )
                OutlinedTextField(
                    label = { Text("Birth date *") },
                    placeholder = { Text("MM/DD/YYYY") },
                    value = birthDate,
                    onValueChange = { birthDate = it },
                    modifier = Modifier.fillMaxWidth(),
                )
                OutlinedTextField(
                    label = { Text("Height *") },
                    placeholder = { Text("Not set") },
                    value = height.toString(),
                    onValueChange = { height = it.toIntOrNull() ?: 0 },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                OutlinedTextField(
                    label = { Text("Weight *") },
                    placeholder = { Text("Not set") },
                    value = weight.toString(),
                    onValueChange = { weight = it.toIntOrNull() ?: 0 },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                OutlinedTextField(
                    label = { Text("Blood type *") },
                    placeholder = { Text("Not set") },
                    value = bloodType,
                    onValueChange = { bloodType = it },
                    modifier = Modifier.fillMaxWidth(),
                )
                OutlinedTextField(
                    label = { Text("Allergies") },
                    placeholder = { Text("Not set") },
                    value = allergies,
                    onValueChange = { allergies = it },
                    modifier = Modifier.fillMaxWidth(),
                )
                OutlinedTextField(
                    label = { Text("Medications") },
                    placeholder = { Text("Not set") },
                    value = medications,
                    onValueChange = { medications = it },
                    modifier = Modifier.fillMaxWidth(),
                )
                OutlinedTextField(
                    label = { Text("Medical Notes") },
                    placeholder = { Text("Not set") },
                    value = medicalNotes,
                    onValueChange = { medicalNotes = it },
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}