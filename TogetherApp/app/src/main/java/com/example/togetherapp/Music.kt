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
    private lateinit var seekBar3: SeekBar
    private lateinit var seekBar4: SeekBar
    private lateinit var seekBar5: SeekBar

    private var timer: Timer? = null
    private var currentPlayingToggle: ToggleButton? = null

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
                            currentPlayingToggle = null
                        }
                    }
                    mediaPlayer.start()
                    if (seekBar1.progress > 0) { // Check if progress is not at the beginning
                        mediaPlayer.seekTo(seekBar1.progress) // Resume from the current progress
                    }
                    seekBar1.max = mediaPlayer.duration
                    startSeekBarUpdate(seekBar1, mediaPlayer)
                    currentPlayingToggle = playPauseToggle1
                    playPauseToggle1.isChecked = true
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
                            currentPlayingToggle = null
                        }
                    }
                    mediaPlayer.start()
                    if (seekBar2.progress > 0) {
                        mediaPlayer.seekTo(seekBar2.progress)
                    }
                    seekBar2.max = mediaPlayer.duration
                    startSeekBarUpdate(seekBar2, mediaPlayer)
                    currentPlayingToggle = playPauseToggle2
                    playPauseToggle2.isChecked = true
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            } else {
                mediaPlayer.pause()
                stopSeekBarUpdate()
            }
        }

        val playPauseToggle3 = findViewById<ToggleButton>(R.id.play_pause_toggle3)
        playPauseToggle3.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                stopMediaPlayer()
                try {
                    mediaPlayer.setDataSource("https://firebasestorage.googleapis.com/v0/b/togetherapp-cabdf.appspot.com/o/dcpoke__birds-singing-03.mp3?alt=media&token=ec1343d7-236f-487d-b9e8-0a1bc7d7629e")
                    mediaPlayer.prepare()
                    mediaPlayer.setOnCompletionListener {
                        playPauseToggle1.post {
                            mediaPlayer.reset()
                            seekBar3.progress = 0
                            playPauseToggle3.isChecked = false
                            currentPlayingToggle = null
                        }
                    }
                    mediaPlayer.start()
                    if (seekBar3.progress > 0) {
                        mediaPlayer.seekTo(seekBar3.progress) 
                    }
                    seekBar3.max = mediaPlayer.duration
                    startSeekBarUpdate(seekBar3, mediaPlayer)
                    currentPlayingToggle = playPauseToggle3
                    playPauseToggle3.isChecked = true
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            } else {
                mediaPlayer.pause()
                stopSeekBarUpdate()
            }
        }

        val playPauseToggle4 = findViewById<ToggleButton>(R.id.play_pause_toggle4)
        playPauseToggle4.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                stopMediaPlayer()
                try {
                    mediaPlayer.setDataSource("https://firebasestorage.googleapis.com/v0/b/togetherapp-cabdf.appspot.com/o/cagancelik__river-stream-subtle-slow-gentle.mp3?alt=media&token=67246d55-8861-43b7-8ccc-049108ed2bcf")
                    mediaPlayer.prepare()
                    mediaPlayer.setOnCompletionListener {
                        playPauseToggle4.post {
                            mediaPlayer.reset()
                            seekBar4.progress = 0
                            playPauseToggle4.isChecked = false
                            currentPlayingToggle = null
                        }
                    }
                    mediaPlayer.start()
                    if (seekBar4.progress > 0) {
                        mediaPlayer.seekTo(seekBar4.progress)
                    }
                    seekBar4.max = mediaPlayer.duration
                    startSeekBarUpdate(seekBar4, mediaPlayer)
                    currentPlayingToggle = playPauseToggle4
                    playPauseToggle4.isChecked = true
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            } else {
                mediaPlayer.pause()
                stopSeekBarUpdate()
            }
        }

        val playPauseToggle5 = findViewById<ToggleButton>(R.id.play_pause_toggle5)
        playPauseToggle5.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                stopMediaPlayer()
                try {
                    mediaPlayer.setDataSource("https://firebasestorage.googleapis.com/v0/b/togetherapp-cabdf.appspot.com/o/burghrecords__birds-singing-forest-scotland.mp3?alt=media&token=d287f011-4806-40c4-8b23-4c55f4e72d42")
                    mediaPlayer.prepare()
                    mediaPlayer.setOnCompletionListener {
                        playPauseToggle5.post {
                            mediaPlayer.reset()
                            seekBar5.progress = 0
                            playPauseToggle5.isChecked = false
                            currentPlayingToggle = null
                        }
                    }
                    mediaPlayer.start()
                    if (seekBar5.progress > 0) {
                        mediaPlayer.seekTo(seekBar5.progress)
                    }
                    seekBar5.max = mediaPlayer.duration
                    startSeekBarUpdate(seekBar5, mediaPlayer)
                    currentPlayingToggle = playPauseToggle5
                    playPauseToggle5.isChecked = true
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

        seekBar3 = findViewById<SeekBar>(R.id.seekBar3)
        seekBar3.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
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

        seekBar4 = findViewById<SeekBar>(R.id.seekBar4)
        seekBar4.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
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

        seekBar5 = findViewById<SeekBar>(R.id.seekBar5)
        seekBar5.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
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
        currentPlayingToggle?.isChecked = false
        currentPlayingToggle = null
        mediaPlayer.reset()
        stopSeekBarUpdate()
    }
    override fun onDestroy() {
        super.onDestroy()
        stopMediaPlayer()
    }
}