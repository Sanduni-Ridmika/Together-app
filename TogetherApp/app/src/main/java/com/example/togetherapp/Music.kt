package com.example.togetherapp

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.ToggleButton
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.IOException
import java.util.*

class Music : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var storageRef: StorageReference
    private lateinit var seekBar1: SeekBar
    private lateinit var seekBar2: SeekBar
    private var timer: Timer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)
        storageRef = FirebaseStorage.getInstance().getReference()
        mediaPlayer = MediaPlayer()
        val playPauseToggle1 = findViewById<ToggleButton>(R.id.play_pause_toggle1)
        playPauseToggle1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                stopMediaPlayer()
                try {
                    mediaPlayer.setDataSource("https://firebasestorage.googleapis.com/v0/b/togetherapp-cabdf.appspot.com/o/Calm-and-Peaceful.mp3?alt=media&token=d3f1cfc7-4aae-4e27-bd80-df3f5dcbe47d")
                    mediaPlayer.prepare()
                    mediaPlayer.setOnCompletionListener {
                        playPauseToggle1.post {
                            mediaPlayer.reset()
                            seekBar1.progress = 0
                            playPauseToggle1.isChecked = false
                        }
                    }
                    mediaPlayer.start()
                    seekBar1.max = mediaPlayer.duration
                    startSeekBarUpdate(seekBar1, mediaPlayer)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            } else {
                mediaPlayer.pause()
                stopSeekBarUpdate()
            }
        }

        val playPauseToggle2 = findViewById<ToggleButton>(R.id.play_pause_toggle2)
        playPauseToggle2.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                stopMediaPlayer()
                try {
                    mediaPlayer.setDataSource("https://firebasestorage.googleapis.com/v0/b/togetherapp-cabdf.appspot.com/o/Rain.mp3?alt=media&token=598f75a1-e06c-4a8d-8108-d41981280a13")
                    mediaPlayer.prepare()
                    mediaPlayer.setOnCompletionListener {
                        playPauseToggle2.post {
                            mediaPlayer.reset()
                            seekBar2.progress = 0
                            playPauseToggle2.isChecked = false
                        }
                    }
                    mediaPlayer.start()
                    seekBar2.max = mediaPlayer.duration
                    startSeekBarUpdate(seekBar2, mediaPlayer)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            } else {
                mediaPlayer.pause()
                stopSeekBarUpdate()
            }
        }

        seekBar1 = findViewById<SeekBar>(R.id.seekBar1)
        seekBar1.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        seekBar2 = findViewById<SeekBar>(R.id.seekBar2)
        seekBar2.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        stopMediaPlayer()
    }

    private fun startSeekBarUpdate(seekBar: SeekBar, mediaPlayer: MediaPlayer) {
        timer = Timer()
        timer?.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                seekBar.post {
                    seekBar.progress = mediaPlayer.currentPosition
                }
            }
        }, 0, 500)
    }

    private fun stopSeekBarUpdate() {
        timer?.cancel()
        timer = null
    }

    private fun stopMediaPlayer() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
        }
        mediaPlayer.reset()
        stopSeekBarUpdate()
    }
}