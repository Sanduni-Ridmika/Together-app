package com.example.togetherapp

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.ToggleButton
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.IOException

class Music : AppCompatActivity() {

    private lateinit var mediaPlayer1: MediaPlayer
    private lateinit var mediaPlayer2: MediaPlayer
    private lateinit var storageRef: StorageReference

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
                    mediaPlayer1.setDataSource("https://firebasestorage.googleapis.com/v0/b/togetherapp-cabdf.appspot.com/o/Calm-and-Peaceful.mp3?alt=media&token=d3f1cfc7-4aae-4e27-bd80-df3f5dcbe47d")
                    mediaPlayer1.prepare()
                    mediaPlayer1.start()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            } else {
                // Pause track 1
                mediaPlayer1.pause()
            }
        }

        // Set up MediaPlayer for track 2
        mediaPlayer2 = MediaPlayer()
        val playPauseToggle2 = findViewById<ToggleButton>(R.id.play_pause_toggle2)
        playPauseToggle2.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Play track 2
                try {
                    mediaPlayer2.setDataSource("https://firebasestorage.googleapis.com/v0/b/togetherapp-cabdf.appspot.com/o/Rain.mp3?alt=media&token=598f75a1-e06c-4a8d-8108-d41981280a13")
                    mediaPlayer2.prepare()
                    mediaPlayer2.start()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            } else {
                // Pause track 2
                mediaPlayer2.pause()
            }
        }

        // Set up SeekBar for track 1
        val seekBar1 = findViewById<SeekBar>(R.id.seekBar1)
        seekBar1.max = mediaPlayer1.duration
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
        val seekBar2 = findViewById<SeekBar>(R.id.seekBar2)
        seekBar2.max = mediaPlayer2.duration
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

    override fun onDestroy() {
        super.onDestroy()
        // Release MediaPlayers when activity is destroyed
        mediaPlayer1.release()
        mediaPlayer2.release()
    }
}