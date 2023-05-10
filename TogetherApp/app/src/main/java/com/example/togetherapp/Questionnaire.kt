package com.example.togetherapp

import BaseActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import okhttp3.*
import okhttp3.Request
import okhttp3.Response
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

import okio.IOException
import org.json.JSONArray
import org.json.JSONObject

class Questionnaire : BaseActivity() {

    // defining the questions list
    private val questions = listOf(
        "1. Little interest or pleasure in doing things?",
        "2. Feeling down, depressed, or hopeless?",
        "3. Trouble falling or staying asleep, or sleeping too much?",
        "4. Feeling tired or having little energy?",
        "5. Poor appetite or overeating?",
        "6. Feeling memory loss or having trouble remembering?",
        "7. Feeling bad about yourself or that you are a failure or have let yourself or your family down?",
        "8. Trouble concentrating on things, such as reading the newspaper or watching television?",
        "9. Moving or speaking so slowly or have been moving around a lot more than usual that other people could have noticed?",
        "10. Aches or pains, headaches, cramps, or digestive problems without a clear physical cause and/or that do not ease even with treatment?",
        "11. Thoughts that you would be better off dead, or hurting yourself in some way?"
    )
    private val radioButtonValues = mapOf(
        R.id.option1_radio_button to 0,
        R.id.option2_radio_button to 1,
        R.id.option3_radio_button to 2,
        R.id.option4_radio_button to 3
    )
    private val client = OkHttpClient()

    // Define the current question index
    private var currentQuestionIndex = 0
    private val userResponses = mutableMapOf<Int, Int>()
    //private var prediction: String? = null

    // Get references to the UI elements
    private lateinit var previousButton: Button
    private lateinit var nextButton: Button
    private lateinit var optionsRadioGroup: RadioGroup
    var prediction = ""

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
                //val intent = Intent(this, Results::class.java)
                //startActivity(intent)

                sendResultsToFlask()
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

    private fun sendResultsToFlask() {
        val responses = mutableListOf<Float>()
        for (i in 0 until questions.size) {
            userResponses[i]?.let { response ->
                responses.add(response.toFloat())
            }
        }

        // prepare the JSON request body
        val json = JSONObject()
        json.put("responses", JSONArray(responses))
        val requestBody = json.toString().toRequestBody("application/json".toMediaTypeOrNull())

        // Send the user responses to the Flask server using OkHttp
        val request = Request.Builder()
            .url("  https://together-385821.uc.r.appspot.com/appPredict")
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Display an error message if the HTTP request fails
                runOnUiThread {
                    Toast.makeText(this@Questionnaire, "Failed to submit your responses.", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onResponse(call: Call, response: Response) {
                val responseMediaType = response.body?.contentType()?.toString() ?: ""
                if (responseMediaType == "application/json") {
                    // the response is in JSON format
                    val jsonResponse = response.body?.string() ?: "{}"
                    val jsonObject = JSONObject(jsonResponse)
                    prediction = jsonObject.optString("prediction", "Unknown error")

                } else {
                    // the response is not in JSON format
                    prediction = response.body?.string() ?: "Unknown error"

                }
                val intent = Intent(this@Questionnaire, Results::class.java).apply {
                    putExtra("prediction", prediction)
                }
                startActivity(intent)
            }

        })
    }

}

/*
            override fun onResponse(call: Call, response: Response) {

                // save prediction response
                val prediction = response.body?.string() ?: "Unknown error"

                // Display the response body in a toast message
                runOnUiThread {
                    Toast.makeText(this@Questionnaire, "Response: $prediction", Toast.LENGTH_LONG).show()
                }
                /*
                val intent = Intent(this@Questionnaire, Results::class.java).apply {
                    putExtra("prediction", prediction)
                }
                startActivity(intent)

                // Display a success message if the HTTP request succeeds
                runOnUiThread {
                    Toast.makeText(this@Questionnaire, "Responses have been submitted successfully.", Toast.LENGTH_SHORT).show()
                }*/

 */