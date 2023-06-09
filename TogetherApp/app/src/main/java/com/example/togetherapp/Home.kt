package com.example.togetherapp

import BaseActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton
import android.widget.PopupMenu
import com.google.firebase.auth.FirebaseAuth

class Home : BaseActivity() {
    lateinit var button1: Button
    lateinit var button2: Button
    lateinit var button3: Button
    lateinit var button4: Button

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
            val intent = Intent(this, Meditations::class.java)
            startActivity(intent)
        }

        button4 = findViewById(R.id.button4)
        button4.setOnClickListener {
            val intent = Intent(this, SupportGroup::class.java)
            startActivity(intent)
        }
    }
}