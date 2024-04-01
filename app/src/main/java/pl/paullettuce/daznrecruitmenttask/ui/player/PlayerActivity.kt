package pl.paullettuce.daznrecruitmenttask.ui.player

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import pl.paullettuce.daznrecruitmenttask.R
import pl.paullettuce.daznrecruitmenttask.databinding.ActivityPlayerBinding
import pl.paullettuce.daznrecruitmenttask.ui.model.ViewError
import pl.paullettuce.daznrecruitmenttask.ui.utils.toast


private const val VIDEO_URL_KEY = "VIDEO_URL_KEY"

class PlayerActivity : Activity() {

    private lateinit var binding: ActivityPlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_player)
        preparePlayer()
    }

    override fun onDestroy() {
        binding.playerView.player?.release()
        super.onDestroy()
    }

    private fun preparePlayer() = with(binding.playerView) {
        val builder = ExoPlayer.Builder(this@PlayerActivity)
        val player = builder.build()
        setPlayer(player)
        player.addListener(object : Player.Listener {
            override fun onPlayerError(error: PlaybackException) {
                super.onPlayerError(error)
                toast(error.mapToErrorType().headerRes).show()
            }
        })
        player.setMediaItem(MediaItem.fromUri(intent.getStringExtra(VIDEO_URL_KEY).orEmpty()))
        player.prepare()
    }

    // Absolutely basic error handling
    private fun PlaybackException.mapToErrorType() = when (errorCode) {
        PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_FAILED -> ViewError.NoNetwork
        else -> ViewError.UnknownError
    }

    companion object {

        fun launch(context: Context, videoUrl: String) {
            context.startActivity(
                Intent(context, PlayerActivity::class.java)
                    .putExtra(VIDEO_URL_KEY, videoUrl)
            )
        }
    }
}