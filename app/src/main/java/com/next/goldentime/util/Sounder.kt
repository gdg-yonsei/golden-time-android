package com.next.goldentime.util

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool

fun play(context: Context, resource: Int): SoundPool {
    val soundPlayer = SoundPool.Builder()
        .setAudioAttributes(
            AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ALARM)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build()
        )
        .setMaxStreams(10)
        .build()

    soundPlayer.load(context, resource, 1)
    soundPlayer.setOnLoadCompleteListener { player, soundId, _ ->
        player.play(soundId, 1f, 1f, 1, -1, 1f)
    }

    return soundPlayer
}
