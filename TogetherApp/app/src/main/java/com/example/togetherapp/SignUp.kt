package com.example.togetherapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

class SignUp : AppCompatActivity() {

    private lateinit var edtName: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnSignUp: Button
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mAuth = FirebaseAuth.getInstance()

        edtName = findViewById(R.id.edt_name)
        edtEmail = findViewById(R.id.edt_email)
        edtPassword = findViewById(R.id.edt_password)
        btnSignUp = findViewById(R.id.btnSignUp)

        btnSignUp.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()

            signUp(email, password)
        }
    }
    private fun signUp(email: String, password: String) {
        //creating user
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign up success, update user profile with display name
                    val user = mAuth.currentUser
                    val displayName = edtName.text.toString()
                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setDisplayName(displayName)
                        .build()

                    user?.updateProfile(profileUpdates)
                        ?.addOnCompleteListener { updateTask ->
                            if (updateTask.isSuccessful) {
                                // Display name updated successfully
                                val intent = Intent(this@SignUp, Home::class.java)
                                startActivity(intent)
                            } else {
                                // Failed to update display name
                                Toast.makeText(this@SignUp, "Failed to update display name.", Toast.LENGTH_SHORT).show()
                            }
                        }

                } else {
                    // If sign in fails, display a message to the user.
                    val errorMessage: String = when {
                        task.exception?.message?.contains("already in use") == true -> "User already exists!"
                        task.exception?.message?.contains("The email address is badly formatted") == true -> "Please give a valid email!."
                        task.exception?.message?.contains("The given password is invalid") == true -> "Password is not strong enough!."
                        else -> "SignUp failed."
                    }
                    Toast.makeText(this@SignUp, errorMessage, Toast.LENGTH_SHORT).show()
                    //Toast.makeText(this@SignUp,"SignUp failed", Toast.LENGTH_SHORT).show()
                }
            }
    }
}