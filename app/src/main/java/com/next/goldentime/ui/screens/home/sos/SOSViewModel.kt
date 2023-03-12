package com.next.goldentime.ui.screens.home.sos

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.next.goldentime.ui.SOSActivity

class SOSViewModel : ViewModel() {
    fun triggerSOS(context: Context) {
        context.startActivity(Intent(context, SOSActivity::class.java))
    }
}
