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

        val text1 = findViewById<TextView>(R.id.text1)
        val text2 = findViewById<TextView>(R.id.text2)
        // Get the prediction from the intent extra
        val prediction = intent.getStringExtra("prediction")

        // Display the prediction in the text1 TextView
        text1.text = "$prediction"

        if(prediction=="Not Depressed"){
            text2.text="Not Depressed"
        }else if(prediction=="Depressed"){
            text2.text="Depressed"
        }else if(prediction=="Severely Depressed"){
            text2.text="Severely Depressed"
        }

    }
}