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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.next.goldentime.repository.profile.User
import com.next.goldentime.ui.components.common.Suspender
import com.next.goldentime.ui.components.common.TopBar
import com.next.goldentime.ui.components.common.TopBarIcon
import com.next.goldentime.ui.components.home.WithTopBar
import com.next.goldentime.ui.screens.home.profile.ProfileViewModel
import kotlinx.coroutines.launch

@Composable
fun ProfileEditScreen(model: ProfileViewModel, navigateBack: () -> Unit) {
    val composeScope = rememberCoroutineScope()

    var savedProfile by remember { mutableStateOf<User?>(null) }
    var newProfile by remember { mutableStateOf<User?>(null) }

    LaunchedEffect(Unit) {
        val user = model.getUser()
        savedProfile = user
    }

    WithTopBar(topBar = {
        TopBar(
            "Profile Edit",
            left = TopBarIcon(Icons.Outlined.ArrowBack) { navigateBack() },
            right = TopBarIcon(Icons.Outlined.Done) {
                composeScope.launch {
                    newProfile?.let { model.saveMedicalID(it) }
                    navigateBack()
                }
            },
        )
    }) {
        Suspender(data = savedProfile) {
            val scrollState = rememberScrollState()

            var name by remember { mutableStateOf(it.name) }
            var birthDate by remember { mutableStateOf(it.birthDate) }
            var height by remember { mutableStateOf(it.height) }
            var weight by remember { mutableStateOf(it.weight) }
            var bloodType by remember { mutableStateOf(it.bloodType) }
            var allergies by remember { mutableStateOf(it.allergies) }
            var medications by remember { mutableStateOf(it.medications) }
            var medicalNotes by remember { mutableStateOf(it.medicalNotes) }

            LaunchedEffect(
                name,
                birthDate,
                height,
                weight,
                bloodType,
                allergies,
                medications,
                medicalNotes
            ) {
                newProfile = User(
                    name,
                    birthDate,
                    height,
                    weight,
                    bloodType,
                    allergies,
                    medications,
                    medicalNotes,
                    it.diseases
                )
            }

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