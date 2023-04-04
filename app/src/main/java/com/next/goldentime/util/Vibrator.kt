package com.next.goldentime.util

import android.content.Context
import android.os.*
import androidx.annotation.RequiresApi

private val vibrationPattern = longArrayOf(500, 500)

fun vibrate(context: Context): Vibrator {
    return when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> vibrateS(context)
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> vibrateO(context)
        else -> vibrateLegacy(context)
    }
}

@RequiresApi(Build.VERSION_CODES.S)
private fun vibrateS(context: Context): Vibrator {
    val vibratorManager =
        (context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager)
    val vibrator = vibratorManager.defaultVibrator
    val vibrationEffect = VibrationEffect.createWaveform(vibrationPattern, 0)

    vibrator.vibrate(vibrationEffect)

    return vibrator
}

@RequiresApi(Build.VERSION_CODES.O)
private fun vibrateO(context: Context): Vibrator {
    val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    val vibrationEffect = VibrationEffect.createWaveform(vibrationPattern, 0)

    vibrator.vibrate(vibrationEffect)

    return vibrator
}

private fun vibrateLegacy(context: Context): Vibrator {
    val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

    vibrator.vibrate(vibrationPattern, 0)

    return vibrator
}