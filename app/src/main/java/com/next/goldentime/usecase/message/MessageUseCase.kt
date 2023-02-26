package com.next.goldentime.usecase.message

import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging

object MessageUseCase {
    fun subscribeSOS(onSuccess: () -> Unit = {}, onFailure: () -> Unit = {}) {
        FirebaseMessaging.getInstance().subscribeToTopic("SOS").addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("SOS Subscription", "SUCCEEDED")
                onSuccess()
            } else {
                Log.d("SOS Subscription", "FAILED")
                onFailure()
            }
        }
    }
}