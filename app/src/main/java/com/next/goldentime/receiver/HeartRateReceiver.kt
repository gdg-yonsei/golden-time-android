package com.next.goldentime.receiver

import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.google.android.gms.wearable.MessageEvent
import com.google.android.gms.wearable.WearableListenerService
import com.next.goldentime.ui.SOSActivity
import com.next.goldentime.usecase.patient.SOSType

class HeartRateReceiver : WearableListenerService() {
    override fun onMessageReceived(messageEvent: MessageEvent) {
        if (messageEvent.path != "/heart-rate") return

        val heartBpm = messageEvent.data.toString().toIntOrNull() ?: 50

        if (heartBpm < 40 || heartBpm > 100) {
            Handler(Looper.getMainLooper()).post {
                val intent = Intent(this, SOSActivity::class.java)
                intent.putExtra("sosType", SOSType.HEART)

                startActivity(intent)
            }
        }
    }
}
