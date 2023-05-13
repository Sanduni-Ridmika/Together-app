package com.example.togetherapp

import BaseActivity
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.BulletSpan
import android.text.style.StyleSpan
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
            val causes = " A combination of genetic, environmental, and psychological factors\n" +
                    " A family history of depression\n" +
                    " Significant life changes or stressful events\n" +
                    " Chemical imbalances in the brain\n" +
                    " Hormonal changes, such as those during pregnancy or menopause\n" +
                    " Substance abuse or addiction\n" +
                    " Social isolation or loneliness\n"+
                    " Poor nutrition and lack of physical exercise\n"+
                    " Low self-esteem or negative self-talk\n" +
                    " Certain medications or medical treatments\n" +
                    " Personality traits such as perfectionism or pessimism."

            // Convert causes text to bullet list HTML
            val bulletListHTML = "<ul><li>&nbsp;${causes.replace("\n", "</li><li>&nbsp;")}</li></ul>"

            val intent = Intent(this, InfoDisplay::class.java)
            intent.putExtra("question", "What is Depression?")
            intent.putExtra("detail", "Depression is a mental health disorder characterized by persistent feelings of sadness, hopelessness, and a lack of interest or pleasure in everyday activities.\n" + "It can also involve physical symptoms such as fatigue, changes in appetite or sleep patterns, and difficulty concentrating.\n\n" +
                    "<br> <br> <b> Causes of Depression,</b>" +  bulletListHTML)
            startActivity(intent)

        }


        // Set up click listener for button2
        button2 = findViewById(R.id.button2)
        button2.setOnClickListener {
            val intent = Intent(this, InfoDisplay::class.java)
            intent.putExtra("question", "Symptoms of Depression")
            intent.putExtra("detail", " Persistent feelings of sadness, emptiness, or hopelessness\n" +
                    " Loss of interest or pleasure in activities that used to be enjoyable\n" +
                    " Fatigue or lack of energy\n" +
                    " Changes in appetite or weight\n" +
                    " Difficulty sleeping or sleeping too much\n" +
                    " Restlessness or irritability\n" +
                    " Difficulty concentrating or making decisions\n" +
                    " Feelings of worthlessness or guilt\n" +
                    " Thoughts of suicide or self-harm")
            startActivity(intent)
        }

        // Set up click listener for button3
        button3 = findViewById(R.id.button3)
        button3.setOnClickListener {
            val intent = Intent(this, InfoDisplay::class.java)
            intent.putExtra("question", "Treatments for Depression")
            intent.putExtra("detail", " Talk therapy with a mental health professional\n" +
                    " Medications prescribed by a doctor or psychiatrist\n" +
                    " Exercise or physical activity\n" +
                    " Mindfulness-based practices like meditation or yoga\n" +
                    " Listening to mind relaxing music\n" +
                    " Mind relaxing exercises such as breathing exercises\n" +
                    " Lifestyle changes such as improving sleep habits and reducing stress")
            startActivity(intent)
        }

        // Set up click listener for button4
        button4 = findViewById(R.id.button4)
        button4.setOnClickListener {
            val intent = Intent(this, InfoDisplay::class.java)
            intent.putExtra("question", "Local Support Centers")
            intent.putExtra("detail", "National Institute of Mental Health <br><a href='tel:0112695295'>011 269 5295</a>\n" +
                    "Sumithrayo <br><a href='tel:0112696666'>011 269 6666</a>\n" +
                    "CCC Foundation <br><a href='tel:0777744242'>077 774 4242</a>\n" +
                    "Institute for Psychological Health <br><a href='tel:0773844224'>077 384 4224</a>\n" +
                    "National Council for Mental Health <br><a href='tel:0112886786'>011 288 6786</a>")
            startActivity(intent)
        }
    }
}