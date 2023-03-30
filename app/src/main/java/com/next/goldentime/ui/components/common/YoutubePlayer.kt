package com.next.goldentime.ui.components.common

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun YoutubePlayer(url: String) {
    val videoId =
        when {
            url.startsWith("http") && url.contains("v=") -> url.split("=").last()
            url.startsWith("http") && !url.contains("v=") -> url.split("/").last()
            else -> url
        }

    Surface {
        AndroidView(factory = { context ->
            val player = YouTubePlayerView(context)
            player.addYouTubePlayerListener(
                object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        super.onReady(youTubePlayer)
                        youTubePlayer.loadVideo(videoId, 0f)
                    }
                })

            player
        })
    }
}