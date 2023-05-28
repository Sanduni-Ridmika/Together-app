package com.example.togetherapp

import BaseActivity
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.ToggleButton
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.IOException
import java.util.*

class Music : BaseActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var storageRef: StorageReference
    private lateinit var seekBar1: SeekBar
    private lateinit var seekBar2: SeekBar
    private lateinit var seekBar3: SeekBar
    private lateinit var seekBar4: SeekBar
    private lateinit var seekBar5: SeekBar
    private lateinit var seekBar6: SeekBar
    private lateinit var seekBar7: SeekBar
    private lateinit var seekBar8: SeekBar

    private var timer: Timer? = null
    private var currentPlayingToggle: ToggleButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)

        //home button action
        val homeButton = findViewById<ImageButton>(R.id.homebutton)
        homeButton.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
            finish()
        }

        storageRef = FirebaseStorage.getInstance().getReference()
        mediaPlayer = MediaPlayer()

        val playPauseToggle1 = findViewById<ToggleButton>(R.id.play_pause_toggle1)
        playPauseToggle1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                stopMediaPlayer()
                try {
                    mediaPlayer.setDataSource("https://firebasestorage.googleapis.com/v0/b/togetherapp-cabdf.appspot.com/o/0057.%20Asian%20-%20AShamaluevMusic.mp3?alt=media&token=56f0b9b4-37d8-4db1-a3c6-0dd672fd512f")
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
                    mediaPlayer.setDataSource("https://firebasestorage.googleapis.com/v0/b/togetherapp-cabdf.appspot.com/o/mixkit-yoga-tune-325.mp3?alt=media&token=92eff05f-171a-459e-ae72-9a36c0c23cb6")
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
                    mediaPlayer.setDataSource("https://firebasestorage.googleapis.com/v0/b/togetherapp-cabdf.appspot.com/o/mixkit-spiritual-moment-525.mp3?alt=media&token=2c691f55-322e-421a-977e-a9302291d296")
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

        val playPauseToggle6 = findViewById<ToggleButton>(R.id.play_pause_toggle6)
        playPauseToggle6.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                stopMediaPlayer()
                try {
                    mediaPlayer.setDataSource("https://firebasestorage.googleapis.com/v0/b/togetherapp-cabdf.appspot.com/o/dcpoke__birds-singing-03.mp3?alt=media&token=ec1343d7-236f-487d-b9e8-0a1bc7d7629e")
                    mediaPlayer.prepare()
                    mediaPlayer.setOnCompletionListener {
                        playPauseToggle6.post {
                            mediaPlayer.reset()
                            seekBar6.progress = 0
                            playPauseToggle1.isChecked = false
                            currentPlayingToggle = null
                        }
                    }
                    mediaPlayer.start()
                    if (seekBar6.progress > 0) { // Check if progress is not at the beginning
                        mediaPlayer.seekTo(seekBar6.progress) // Resume from the current progress
                    }
                    seekBar6.max = mediaPlayer.duration
                    startSeekBarUpdate(seekBar6, mediaPlayer)
                    currentPlayingToggle = playPauseToggle6
                    playPauseToggle6.isChecked = true
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            } else {
                mediaPlayer.pause()
                stopSeekBarUpdate()
            }
        }

        val playPauseToggle7 = findViewById<ToggleButton>(R.id.play_pause_toggle7)
        playPauseToggle7.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                stopMediaPlayer()
                try {
                    mediaPlayer.setDataSource("https://firebasestorage.googleapis.com/v0/b/togetherapp-cabdf.appspot.com/o/mixkit-nature-meditation-345.mp3?alt=media&token=0e99fefe-83f6-4816-a84a-13eca7c9190d")
                    mediaPlayer.prepare()
                    mediaPlayer.setOnCompletionListener {
                        playPauseToggle7.post {
                            mediaPlayer.reset()
                            seekBar7.progress = 0
                            playPauseToggle7.isChecked = false
                            currentPlayingToggle = null
                        }
                    }
                    mediaPlayer.start()
                    if (seekBar7.progress > 0) {
                        mediaPlayer.seekTo(seekBar7.progress)
                    }
                    seekBar7.max = mediaPlayer.duration
                    startSeekBarUpdate(seekBar7, mediaPlayer)
                    currentPlayingToggle = playPauseToggle7
                    playPauseToggle7.isChecked = true
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            } else {
                mediaPlayer.pause()
                stopSeekBarUpdate()
            }
        }

        val playPauseToggle8 = findViewById<ToggleButton>(R.id.play_pause_toggle8)
        playPauseToggle8.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                stopMediaPlayer()
                try {
                    mediaPlayer.setDataSource("https://firebasestorage.googleapis.com/v0/b/togetherapp-cabdf.appspot.com/o/burghrecords__birds-singing-forest-scotland.mp3?alt=media&token=d287f011-4806-40c4-8b23-4c55f4e72d42")
                    mediaPlayer.prepare()
                    mediaPlayer.setOnCompletionListener {
                        playPauseToggle8.post {
                            mediaPlayer.reset()
                            seekBar8.progress = 0
                            playPauseToggle8.isChecked = false
                            currentPlayingToggle = null
                        }
                    }
                    mediaPlayer.start()
                    if (seekBar8.progress > 0) {
                        mediaPlayer.seekTo(seekBar8.progress)
                    }
                    seekBar8.max = mediaPlayer.duration
                    startSeekBarUpdate(seekBar8, mediaPlayer)
                    currentPlayingToggle = playPauseToggle8
                    playPauseToggle8.isChecked = true
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

        seekBar6 = findViewById<SeekBar>(R.id.seekBar6)
        seekBar6.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
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

        seekBar7 = findViewById<SeekBar>(R.id.seekBar7)
        seekBar7.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
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

        seekBar8 = findViewById<SeekBar>(R.id.seekBar8)
        seekBar8.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
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