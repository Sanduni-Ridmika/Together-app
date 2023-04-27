package com.example.togetherapp

import BaseActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class InfoDisplay : BaseActivity() {
    private lateinit var question_text: TextView
    private lateinit var detail_text: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_display)

        question_text = findViewById(R.id.question_text)
        detail_text = findViewById(R.id.detail_text)

        // Get the text passed from Information page
        val question = intent.getStringExtra("question")
        val detail = intent.getStringExtra("detail")

        // Update TextViews with appropriate text
        question_text.text = question
        detail_text.text = detail

    }
}