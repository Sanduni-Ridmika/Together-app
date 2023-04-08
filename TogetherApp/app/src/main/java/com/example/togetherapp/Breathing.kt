package com.example.togetherapp

import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView

class Breathing : AppCompatActivity() {

    private lateinit var playButton: Button
    private lateinit var timerText: TextView
    private lateinit var breathText: TextView

    private lateinit var countDownTimer: CountDownTimer
    private lateinit var breathCountDownTimer: CountDownTimer
    private var isTimerRunning = false
    private var isTimerPaused = false
    private var secondsRemaining = 60
    private var breathDuration = 3000L


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_breathing)


        playButton = findViewById(R.id.button)
        timerText = findViewById(R.id.timer_text)
        breathText = findViewById(R.id.breath_text)

        // Set the background of the play button to the play icon inside a round button drawable
        val drawable = resources.getDrawable(R.drawable.play, null)
        playButton.background = getCombinedDrawable(drawable)

        playButton.setOnClickListener {
            if (!isTimerRunning) {
                startTimer()
            } else if (isTimerPaused) {
                resumeTimer()
            } else {
                pauseTimer()
            }
        }

        breathText.visibility = View.INVISIBLE
    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer((secondsRemaining * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                secondsRemaining--
                updateTimerText()
            }

            override fun onFinish() {
                isTimerRunning = false
                isTimerPaused = false
                secondsRemaining = 60
                updateButtonIcon()
                timerText.text = "00:00"
                breathText.visibility = View.INVISIBLE
                breathCountDownTimer.cancel()
                val drawable = resources.getDrawable(R.drawable.play, null)
                playButton.background = getCombinedDrawable(drawable)
            }
        }

        isTimerRunning = true
        countDownTimer.start()
        updateButtonIcon()

        breathText.text = "Breathe In"
        breathText.visibility = View.VISIBLE

        breathCountDownTimer = object : CountDownTimer(60000, breathDuration) {
            //object : CountDownTimer(60000, 3000) {
            override fun onTick(millisUntilFinished: Long) {
                if (breathText.text == "Breathe In") {
                    breathText.text = "Breathe Out"
                } else {
                    breathText.text = "Breathe In"
                }
            }

            override fun onFinish() {
                // Do nothing
            }
        }
        breathCountDownTimer.start()
//.start()
    }

    private fun pauseTimer() {
        countDownTimer.cancel()
        breathCountDownTimer.cancel()
        isTimerPaused = true
        updateButtonIcon()
    }

    private fun resumeTimer() {
        startTimer()
        isTimerPaused = false
        updateButtonIcon()
    }

    private fun updateTimerText() {
        val minutes = secondsRemaining / 60
        val seconds = secondsRemaining % 60
        timerText.text = String.format("%02d:%02d", minutes, seconds)
    }
    private fun updateButtonIcon() {
        if (!isTimerRunning) {
            playButton.setBackgroundResource(R.drawable.round_button)
        } else if (isTimerPaused) {
            val drawable = resources.getDrawable(R.drawable.play, null)
            playButton.background = getCombinedDrawable(drawable)
        } else {
            val drawable = resources.getDrawable(R.drawable.pause, null)
            playButton.background = getCombinedDrawable(drawable)
        }
    }

    private fun getCombinedDrawable(drawable: Drawable): Drawable {
        val layers = arrayOf(
            resources.getDrawable(R.drawable.round_button, null),
            drawable
        )
        val layerDrawable = LayerDrawable(layers)
        layerDrawable.setLayerInset(1, 0, 0, 0, 0)
        return layerDrawable
    }
}