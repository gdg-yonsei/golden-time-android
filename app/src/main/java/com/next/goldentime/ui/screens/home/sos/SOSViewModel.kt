package com.next.goldentime.ui.screens.home.sos

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.next.goldentime.ui.SOSActivity
import com.next.goldentime.usecase.patient.SOSType

class SOSViewModel : ViewModel() {
    fun triggerSOS(context: Context) {
        val intent = Intent(context, SOSActivity::class.java)
        intent.putExtra("sosType", SOSType.DIRECT)

        context.startActivity(intent)
    }
}
