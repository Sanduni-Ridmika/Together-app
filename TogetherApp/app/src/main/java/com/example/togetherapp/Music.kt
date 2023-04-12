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

    private lateinit var mediaPlayer1: MediaPlayer
    private lateinit var mediaPlayer2: MediaPlayer
    private lateinit var storageRef: StorageReference
    private lateinit var seekBar1: SeekBar
    private lateinit var seekBar2: SeekBar
    private var timer: Timer? = null
    private var mediaPlayer1CurrentPosition: Int = 0 // Variable to store current position of mediaPlayer1
    private var mediaPlayer2CurrentPosition: Int = 0 // Variable to store current position of mediaPlayer2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)

        // Initialize Firebase Storage
        storageRef = FirebaseStorage.getInstance().getReference()

        // Set up MediaPlayer for track 1
        mediaPlayer1 = MediaPlayer()
        val playPauseToggle1 = findViewById<ToggleButton>(R.id.play_pause_toggle1)
        playPauseToggle1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Play track 1
                try {
                    if (mediaPlayer1CurrentPosition == 0) {
                        // If starting from beginning, set data source and prepare
                        mediaPlayer1.setDataSource("https://firebasestorage.googleapis.com/v0/b/togetherapp-cabdf.appspot.com/o/Calm-and-Peaceful.mp3?alt=media&token=d3f1cfc7-4aae-4e27-bd80-df3f5dcbe47d")
                        mediaPlayer1.prepare()
                    } else {
                        // If resuming from stopped position, seek to saved position
                        mediaPlayer1.seekTo(mediaPlayer1CurrentPosition)
                    }
                    mediaPlayer1.start()
                    seekBar1.max = mediaPlayer1.duration
                    startSeekBarUpdate(seekBar1, mediaPlayer1)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            } else {
                // Pause track 1
                mediaPlayer1.pause()
                mediaPlayer1CurrentPosition = mediaPlayer1.currentPosition
                stopSeekBarUpdate()
            }
        }

        // Set up MediaPlayer for track 2
        mediaPlayer2 = MediaPlayer()
        val playPauseToggle2 = findViewById<ToggleButton>(R.id.play_pause_toggle2)
        playPauseToggle2.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Play track 2
                try {
                    if (mediaPlayer2CurrentPosition == 0) {
                        // If starting from beginning, set data source and prepare
                        mediaPlayer2.setDataSource("https://firebasestorage.googleapis.com/v0/b/togetherapp-cabdf.appspot.com/o/Rain.mp3?alt=media&token=598f75a1-e06c-4a8d-8108-d41981280a13")
                        mediaPlayer2.prepare()
                    } else {
                        // If resuming from stopped position, seek to saved position
                        mediaPlayer2.seekTo(mediaPlayer2CurrentPosition)
                    }
                    mediaPlayer2.start()
                    seekBar2.max = mediaPlayer2.duration
                    startSeekBarUpdate(seekBar2, mediaPlayer2)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            } else {
                // Pause track 2
                mediaPlayer2.pause()
                mediaPlayer2CurrentPosition = mediaPlayer2.currentPosition
                stopSeekBarUpdate()
            }
        }

        // Set up SeekBar for track 1
        seekBar1 = findViewById<SeekBar>(R.id.seekBar1)
        seekBar1.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer1.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Nothing to do
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Nothing to do
            }
        })

        // Set up SeekBar for track 2
        seekBar2 = findViewById<SeekBar>(R.id.seekBar2)
        seekBar2.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer2.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Nothing to do
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Nothing to do
            }
        })
    }
    // Method to start updating SeekBar progress
    private fun startSeekBarUpdate(seekBar: SeekBar, mediaPlayer: MediaPlayer) {
        // Cancel any previous timers
        stopSeekBarUpdate()

        // Start a new timer to update SeekBar progress
        timer = Timer()
        timer?.schedule(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    seekBar.progress = mediaPlayer.currentPosition
                }
            }
        }, 0, 500)
    }

    // Method to stop updating SeekBar progress
    private fun stopSeekBarUpdate() {
        timer?.cancel()
        timer = null
    }

    override fun onDestroy() {
        super.onDestroy()
        // Release MediaPlayers when activity is destroyed
        mediaPlayer1.release()
        mediaPlayer2.release()
        // Stop updating SeekBar progress
        stopSeekBarUpdate()
    }
}