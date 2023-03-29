package com.next.goldentime.receiver

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.text.isDigitsOnly
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.next.goldentime.R
import com.next.goldentime.ui.RescueActivity
import com.next.goldentime.util.createNotificationChannel

class SOSReceiver : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("GOLDEN TIME FCM", "New token generated : $token")
    }

    @SuppressLint("MissingPermission")
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        val sosId = remoteMessage.data["sosId"]

        /**
         * Check message validity
         */
        if (sosId == null || !sosId.isDigitsOnly()) return

        /**
         * Check location
         * TODO : Calculate the distance between the patient and the rescuer. If it is not a proper distance, do not show the notification.
         */

        val intent = Intent(this, RescueActivity::class.java)
        intent.putExtra("sosId", sosId.toInt())
        val pendingIntent =
            PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )

        createNotificationChannel(this, "sos", "SOS Request")
        val notification = NotificationCompat.Builder(this, "sos")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Someone needs your help!")
            .setContentText("Look around and check the manual for the patient.")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(this)) {
            notify(1, notification.build())
        }
    }
}