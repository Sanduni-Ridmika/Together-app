package com.example.togetherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class UserProfile : AppCompatActivity() {

    private lateinit var frameLayout: FrameLayout
    private lateinit var txtName: TextView
    private lateinit var txtEmail: TextView
    private lateinit var txtPassword: TextView
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        mAuth = FirebaseAuth.getInstance()

        frameLayout = findViewById(R.id.frame)
        txtName = findViewById(R.id.txt_name)
        txtEmail = findViewById(R.id.txt_email)
        txtPassword = findViewById(R.id.txt_password)

        // Get the currently logged in user
        val currentUser = mAuth.currentUser

        if (currentUser != null) {
            // Set the name, email, and password in the TextViews
            txtName.text = "Name: ${currentUser.displayName}"
            txtEmail.text = "Email: ${currentUser.email}"
            txtPassword.text = "Password: ********"
        }
    }
}