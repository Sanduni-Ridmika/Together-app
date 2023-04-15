package com.example.togetherapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LogIn : AppCompatActivity() {

    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnLogIn: Button
    private lateinit var btnSignUp: Button
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        mAuth = FirebaseAuth.getInstance()

        edtEmail = findViewById(R.id.edt_email)
        edtPassword = findViewById(R.id.edt_password)
        btnLogIn = findViewById(R.id.btnLogin)
        btnSignUp = findViewById(R.id.btnSignUp)

        //naviagte to signup page
        btnSignUp.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

        btnLogIn.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()

            login(email, password);
        }
    }

    private fun login(email: String, password: String){
        //login user
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Login success navigate to home
                    val intent = Intent(this@LogIn, Home::class.java)
                    startActivity(intent)

                } else {
                    // If login fails display a message to the user.
                    val errorMessage: String = when {
                        task.exception?.message?.contains("There is no user record corresponding to this identifier") == true -> "User does not exist."
                        task.exception?.message?.contains("The password is invalid") == true -> "Password is incorrect."
                        task.exception?.message?.contains("The email address is badly formatted") == true -> "Email is incorrect."
                        else -> "Login failed."
                    }
                    Toast.makeText(this@LogIn, errorMessage, Toast.LENGTH_SHORT).show()

                }
            }
    }
}