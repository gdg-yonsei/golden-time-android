package com.next.goldentime.ui.screens.sos.detect

import android.content.Context
import android.media.SoundPool
import android.os.Vibrator
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.next.goldentime.R
import com.next.goldentime.usecase.patient.SOSType
import com.next.goldentime.util.play
import com.next.goldentime.util.vibrate

class SOSDetectViewModel(sosType: SOSType) : ViewModel() {
    private var vibrator: Vibrator? = null
    private var soundPlayer: SoundPool? = null

    val title = when (sosType) {
        SOSType.FALL -> "Fall Detected"
        SOSType.HEART -> "Irregular Heart Rate Detected"
        else -> "SOS"
    }
    val waitingTime = when (sosType) {
        SOSType.FALL, SOSType.HEART -> 30
        else -> 0
    }

    fun startNotify(context: Context) {
        vibrator = vibrate(context)
        soundPlayer = play(context, R.raw.emergency)
    }

    fun stopNotify() {
        vibrator?.cancel()
        soundPlayer?.release()
    }
}

class SOSDetectViewModelFactory(private val sosType: SOSType) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SOSDetectViewModel::class.java)) {
            return SOSDetectViewModel(sosType) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}