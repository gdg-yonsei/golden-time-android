package com.next.goldentime.receiver

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class SOSReceiver : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("GOLDEN TIME FCM", "New token generated : $token")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Handler(Looper.getMainLooper()).post(Runnable {
            Log.d("GOLDEN TIME FCM", "New message received : ${remoteMessage.notification?.body}")
            Toast.makeText(
                this,
                "New message received : ${remoteMessage.notification?.body}",
                Toast.LENGTH_LONG
            ).show()
        })
    }
}