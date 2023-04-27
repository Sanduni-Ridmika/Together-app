package com.example.togetherapp

import BaseActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth

class Questionnaire : BaseActivity() {

    // defining the questions list
    private val questions = listOf(
        "Little interest or pleasure in doing things?",
        "Feeling down, depressed, or hopeless?",
        "Trouble falling or staying asleep, or sleeping too much?",
        "Feeling tired or having little energy?",
        "Poor appetite or overeating?",
        "Feeling memory loss or having trouble remembering?",
        "Feeling bad about yourself or that you are a failure or have let yourself or your family down?",
        "Trouble concentrating on things, such as reading the newspaper or watching television?",
        "Moving or speaking so slowly or have been moving around a lot more than usual that other people could have noticed?",
        "Aches or pains, headaches, cramps, or digestive problems without a clear physical cause and/or that do not ease even with treatment?",
        "Thoughts that you would be better off dead, or hurting yourself in some way?"
    )
    private val radioButtonValues = mapOf(
        R.id.option1_radio_button to 0,
        R.id.option2_radio_button to 1,
        R.id.option3_radio_button to 2,
        R.id.option4_radio_button to 3
    )
    // Define the current question index
    private var currentQuestionIndex = 0
    private val userResponses = mutableMapOf<Int, Int>()

    // Get references to the UI elements
    private lateinit var previousButton: Button
    private lateinit var nextButton: Button
    private lateinit var optionsRadioGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questionnaire)

        // Initialize UI elements
        previousButton = findViewById(R.id.previous_button)
        nextButton = findViewById(R.id.next_button)
        optionsRadioGroup = findViewById(R.id.options_radio_group)

        showCurrentQuestion()

        previousButton.setOnClickListener {
            // Decrement the current question index and update the question text to previous question
            if (currentQuestionIndex > 0) {
                currentQuestionIndex--
                showCurrentQuestion()
            }
        }
        nextButton.setOnClickListener {

            // save the user's response to the current question
            val selectedRadioButtonId = optionsRadioGroup.checkedRadioButtonId
            if (selectedRadioButtonId != -1) {
                userResponses[currentQuestionIndex] = radioButtonValues[selectedRadioButtonId]!!
            }
            else {
                // Display an alert message if the user clicks the "next" button without selecting any radio buttons
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Alert")
                    .setMessage("Please provide an answer.")
                    .setPositiveButton("OK", null)
                builder.create().show()
                return@setOnClickListener
            }

            // Increment the current question index and update the question text to next question
            if (currentQuestionIndex < questions.size - 1) {
                currentQuestionIndex++
                showCurrentQuestion()
            } else {
                // all questions answered, show results page
                val intent = Intent(this, Results::class.java)
                startActivity(intent)
            }
        }
    }

    // display the current question
    private fun showCurrentQuestion() {
        val questionText = findViewById<TextView>(R.id.question_text)
        questionText.text = questions[currentQuestionIndex]
        // show the previously selected option for the current question (if any)
        val previousResponse = userResponses[currentQuestionIndex]
        if (previousResponse != null) {
            val radioButtonId = radioButtonValues.entries.find { it.value == previousResponse }?.key
            if (radioButtonId != null) {
                optionsRadioGroup.check(radioButtonId)
            }
        } else {
            optionsRadioGroup.clearCheck()
        }
    }
}