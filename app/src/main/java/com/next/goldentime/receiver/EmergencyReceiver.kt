package com.next.goldentime.receiver

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.google.android.gms.wearable.MessageEvent
import com.google.android.gms.wearable.WearableListenerService

class EmergencyReceiver : WearableListenerService() {
    override fun onMessageReceived(messageEvent: MessageEvent) {
        Handler(Looper.getMainLooper()).post(Runnable {
            Log.d("GOLDEN TIME FCM", "New message received : ${messageEvent.data}")
            Toast.makeText(
                this,
                "New message received : ${messageEvent.data}",
                Toast.LENGTH_LONG
            ).show()
        })
    }
}
