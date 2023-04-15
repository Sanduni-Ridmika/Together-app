package com.example.togetherapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class SupportGroup : AppCompatActivity() {
    private lateinit var text: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_support_group)

        text = findViewById(R.id.text)
        text.setOnClickListener {
            val intent = Intent(this, UserProfile::class.java)
            startActivity(intent)
        }
    }
}