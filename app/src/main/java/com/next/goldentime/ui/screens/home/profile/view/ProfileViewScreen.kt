package com.next.goldentime.ui.screens.home.profile.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.runtime.Composable
import com.next.goldentime.ui.components.common.FragmentTab
import com.next.goldentime.ui.components.common.TopBar
import com.next.goldentime.ui.components.common.TopBarIcon
import com.next.goldentime.ui.components.home.WithTopBar
import com.next.goldentime.ui.components.home.profile.DiseaseFragment
import com.next.goldentime.ui.components.home.profile.MedicalIDFragment
import com.next.goldentime.ui.screens.home.profile.ProfileViewModel

@Composable
fun ProfileViewScreen(model: ProfileViewModel, edit: () -> Unit) {
    WithTopBar(topBar = {
        TopBar(
            "Profile",
            right = TopBarIcon(Icons.Outlined.Edit) { edit() },
        )
    }) {
        val tabs = listOf("Medical ID", "My medical conditions")

        FragmentTab(tabs = tabs) {
            when (it) {
                0 -> MedicalIDFragment(model)
                1 -> DiseaseFragment(model)
            }
        }
    }
}