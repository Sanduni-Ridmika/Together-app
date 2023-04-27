package com.example.togetherapp

import BaseActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Results : BaseActivity() {

    private lateinit var btnExercise: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        btnExercise = findViewById(R.id.btnExercise)
        btnExercise.setOnClickListener {
            val intent = Intent(this, Meditations::class.java)
            startActivity(intent)
        }
    }
}