package com.next.goldentime.ui.screens.about

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.next.goldentime.ui.RescueActivity

class AboutViewModel : ViewModel() {
    // TEMP
    fun openRescueScreen(context: Context) {
        context.startActivity(Intent(context, RescueActivity::class.java))
    }
}