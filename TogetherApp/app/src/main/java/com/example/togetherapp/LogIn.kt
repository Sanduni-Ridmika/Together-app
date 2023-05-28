package com.example.togetherapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class LogIn : AppCompatActivity() {

    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnLogIn: Button
    private lateinit var btnSignUp: Button
    lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        //FirebaseApp.initializeApp(this);
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

    override fun onStart() {
        super.onStart()
        // Check for user authentication state on app startup
        val currentUser = mAuth.currentUser
        if (currentUser != null) {
            // User is already authenticated, navigate to home page
            val intent = Intent(this@LogIn, Home::class.java)
            startActivity(intent)
            finish() // Finish the current activity to prevent going back to the login page
        }
    }

    fun login(email: String, password: String):Boolean{
        //login user
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this@LogIn, "Please enter both email and password.", Toast.LENGTH_SHORT).show()
            return false
        }
        var r = false
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Login success navigate to home
                    val intent = Intent(this@LogIn, Home::class.java)
                    startActivity(intent)
                    finish()
                    r = true

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
        return r
    }
}