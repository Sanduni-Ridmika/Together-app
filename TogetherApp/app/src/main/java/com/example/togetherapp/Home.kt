package com.example.togetherapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Home : AppCompatActivity() {
    private lateinit var button2: Button
    private lateinit var button1: Button
    private lateinit var button3: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {
            val intent = Intent(this, Questionnaire::class.java)
            startActivity(intent)
        }

        button2 = findViewById(R.id.button2)
        button2.setOnClickListener {
            val intent = Intent(this, Information::class.java)
            startActivity(intent)
        }

        button3 = findViewById(R.id.button3)
        button3.setOnClickListener {
            val intent = Intent(this, Breathing::class.java)
            startActivity(intent)
        }
    }
}