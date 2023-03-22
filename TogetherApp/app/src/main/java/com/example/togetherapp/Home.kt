package com.example.togetherapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Home : AppCompatActivity() {
    private lateinit var button2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        button2 = findViewById(R.id.button2)
        button2.setOnClickListener {
            val intent = Intent(this, Information::class.java)
            startActivity(intent)
        }
    }
}