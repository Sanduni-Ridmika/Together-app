package com.example.togetherapp

import BaseActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Information : BaseActivity() {
    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)

        // Set up click listener for button1
        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {
            val intent = Intent(this, InfoDisplay::class.java)
            intent.putExtra("question", "What is Depression?")
            intent.putExtra("detail", "Depression is a mental health disorder characterized by persistent feelings of sadness, hopelessness, and a loss of interest or pleasure in activities that a person used to enjoy. It can also cause physical symptoms such as fatigue, changes in appetite, and difficulty sleeping.\n" +
                    "The exact causes of depression are not fully understood, but it is believed to be the result of a combination of genetic, environmental, and psychological factors.")
            startActivity(intent)
        }

        // Set up click listener for button2
        button2 = findViewById(R.id.button2)
        button2.setOnClickListener {
            val intent = Intent(this, InfoDisplay::class.java)
            intent.putExtra("question", "Symptoms of Depression")
            intent.putExtra("detail", "• Persistent feelings of sadness, emptiness, or hopelessness\n" +
                    "• Loss of interest or pleasure in activities that used to be enjoyable\n" +
                    "• Fatigue or lack of energy\n" +
                    "• Changes in appetite or weight\n" +
                    "• Difficulty sleeping or sleeping too much\n" +
                    "• Restlessness or irritability\n" +
                    "• Difficulty concentrating or making decisions\n" +
                    "• Feelings of worthlessness or guilt\n" +
                    "• Thoughts of suicide or self-harm\n")
            startActivity(intent)
        }

        // Set up click listener for button3
        button3 = findViewById(R.id.button3)
        button3.setOnClickListener {
            val intent = Intent(this, InfoDisplay::class.java)
            intent.putExtra("question", "Treatments for Depression")
            intent.putExtra("detail", "• Talk therapy with a mental health professional\n" +
                    "• Medications prescribed by a doctor or psychiatrist\n" +
                    "• Exercise or physical activity\n" +
                    "• Mindfulness-based practices like meditation or yoga\n" +
                    "• Listening to mind relaxing music\n" +
                    "• Mind relaxing exercises such as breathing exercises\n" +
                    "• Lifestyle changes such as improving sleep habits and reducing stress\n")
            startActivity(intent)
        }

        // Set up click listener for button4
        button4 = findViewById(R.id.button4)
        button4.setOnClickListener {
            val intent = Intent(this, InfoDisplay::class.java)
            intent.putExtra("question", "Local Support Centers")
            intent.putExtra("detail", "• National Institute of Mental Health - +94 11 2695295\n" +
                    "• Sumithrayo - +94 11 2696666\n" +
                    "• CCC Foundation - +94 77 774 4242\n" +
                    "• Institute for Psychological Health - +94 77 384 4224\n" +
                    "• National Council for Mental Health - +94 11 288 6786\n")
            startActivity(intent)
        }
    }
}