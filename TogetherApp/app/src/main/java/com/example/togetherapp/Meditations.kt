package com.example.togetherapp

import BaseActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Meditations : BaseActivity() {

    private lateinit var button1: Button
    private lateinit var button2: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meditations)

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {
            val intent = Intent(this, Breathing::class.java)
            startActivity(intent)
        }
        button2 = findViewById(R.id.button2)
        button2.setOnClickListener {
            val intent = Intent(this, Music::class.java)
            startActivity(intent)
        }
    }
}