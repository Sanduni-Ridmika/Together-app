package com.example.togetherapp

import BaseActivity
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class Results : BaseActivity() {

    private lateinit var btnExercise: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        val stressMeter = findViewById<ImageView>(R.id.scale)

        btnExercise = findViewById(R.id.btnExercise)
        btnExercise.setOnClickListener {
            val intent = Intent(this, Meditations::class.java)
            startActivity(intent)
        }

        val text1 = findViewById<TextView>(R.id.text1)
        val text2 = findViewById<TextView>(R.id.text2)
        // Get the prediction from the intent extra
        val prediction = intent.getStringExtra("prediction")

        val boldSpan = StyleSpan(Typeface.BOLD)
        val colorSpan = ForegroundColorSpan(Color.parseColor("#b30000"))
        val sizeSpan = AbsoluteSizeSpan(18, true)

        // Display the prediction in the TextView
        if(prediction=="Not Depressed"){
            text1.text= "Congratulations!\n\n" + "you're not suffering from Depression."
            text2.text="The responses you have given indicate that you're not suffering from depression."
            stressMeter.setImageResource(R.drawable.scale1)
        }

        else if(prediction=="Depressed"){
            val spannable = SpannableString("These results indicate that you’re suffering from\n\nDepression")
            spannable.setSpan(boldSpan, 51, 61, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            spannable.setSpan(colorSpan, 51, 61, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            spannable.setSpan(sizeSpan, 51, 61, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            text1.text = spannable
            text2.text= "Note that these results are not meant to be a diagnosis. Therefore, we would always suggest you to seek for professional help and support."
            stressMeter.setImageResource(R.drawable.scale2)
        }

        else if(prediction=="Severely Depressed"){
            val spannable = SpannableString("These results indicate that you’re suffering from\n\nSevere Depression")
            spannable.setSpan(boldSpan, 51, 68, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            spannable.setSpan(colorSpan, 51, 68, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            spannable.setSpan(sizeSpan, 51, 68, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            text1.text = spannable
            text2.text="Note that these results are not meant to be a diagnosis. Therefore, we would always suggest you to seek for professional help and support."
            stressMeter.setImageResource(R.drawable.scale3)
        }
    }
}