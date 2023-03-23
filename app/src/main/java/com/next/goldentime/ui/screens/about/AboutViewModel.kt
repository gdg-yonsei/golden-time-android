package com.next.goldentime.ui.screens.about

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.next.goldentime.ui.RescueActivity
import com.next.goldentime.ui.SOSActivity
import com.next.goldentime.usecase.patient.SOSType

class AboutViewModel : ViewModel() {
    // TEMP
    fun openRescueScreen(context: Context, sosId: Int) {
        val intent = Intent(context, RescueActivity::class.java)
        intent.putExtra("sosId", sosId)

        context.startActivity(intent)
    }

    fun openSOSScreen(context: Context, sosType: SOSType) {
        val intent = Intent(context, SOSActivity::class.java)
        intent.putExtra("sosType", sosType)

        context.startActivity(intent)
    }
}