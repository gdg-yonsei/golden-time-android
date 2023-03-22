package com.next.goldentime.ui.screens.home

import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.next.goldentime.data.REQUIRED_PERMISSIONS

class HomeViewModel : ViewModel() {
    fun checkPermissions(
        context: Context,
        launcher: ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>>
    ) {
        val allGranted = REQUIRED_PERMISSIONS.all {
            ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
        }

        if (!allGranted) launcher.launch(REQUIRED_PERMISSIONS)
    }
}