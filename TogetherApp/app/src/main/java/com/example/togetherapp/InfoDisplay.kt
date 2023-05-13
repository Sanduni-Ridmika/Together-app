package com.example.togetherapp

import BaseActivity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.widget.ImageButton
import android.widget.TextView

class InfoDisplay : BaseActivity() {
    private lateinit var question_text: TextView
    private lateinit var detail_text: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_display)

        //home button action
        val homeButton = findViewById<ImageButton>(R.id.homebutton)
        homeButton.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
            finish()
        }

        question_text = findViewById(R.id.question_text)
        detail_text = findViewById(R.id.detail_text)

        // Get the text passed from Information page
        val question = intent.getStringExtra("question")
        var detail = intent.getStringExtra("detail")

        // Add bullet list HTML to the detail text, except for the first line in button1
        if (question != "What is Depression?") {
            detail = detail?.let { "<ul><li>&nbsp;${it.replace("\n", "</li><li>&nbsp;")}</li></ul>" }
        }

        // Update TextViews with appropriate text
        question_text.text = question
        detail_text.text = fromHtml(detail ?: "")

    }

    @Suppress("DEPRECATION")
    private fun fromHtml(html: String): Spanned {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(html)
        }

    }
}