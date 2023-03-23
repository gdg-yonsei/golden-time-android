package com.next.goldentime.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

fun createNotificationChannel(context: Context, channelID: String, channelName: String) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return

    val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    val notificationChannel =
        NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH)

    notificationManager.createNotificationChannel(notificationChannel)
}