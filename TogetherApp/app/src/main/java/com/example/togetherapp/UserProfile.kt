package com.example.togetherapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.firebase.auth.FirebaseAuth

class UserProfile : AppCompatActivity() {

    private lateinit var frameLayout: FrameLayout
    private lateinit var txtName: TextView
    private lateinit var txtEmail: TextView
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        mAuth = FirebaseAuth.getInstance()

        frameLayout = findViewById(R.id.frame)
        txtName = findViewById(R.id.txt_name)
        txtEmail = findViewById(R.id.txt_email)

        val btnLogout = findViewById<Button>(R.id.btnLogout)
        // Get the currently logged in user
        val currentUser = mAuth.currentUser

        if (currentUser != null) {
            // Set the name, email, and password in the TextViews
            txtName.text = "Name: ${currentUser.displayName}"
            txtEmail.text = "Email: ${currentUser.email}"
        }

        // Set click listener for "Add contacts" button
        val txtAddContacts = findViewById<TextView>(R.id.txt_add_contacts)
        txtAddContacts.setOnClickListener {
            onAddContactsClicked(it)
        }

        btnLogout.setOnClickListener {
            // Log out the user
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, LogIn::class.java)
            startActivity(intent)
            finish() // Finish the current activity to prevent going back to the user profile page
        }
    }
    // Click event handler for "Add contacts" button
    fun onAddContactsClicked(view: View) {
        val layoutContacts = findViewById<LinearLayout>(R.id.layout_contacts)
        val txtAddContacts = findViewById<TextView>(R.id.txt_add_contacts)

        // Toggle visibility of contact input fields
        if (layoutContacts.visibility == View.VISIBLE) {
            layoutContacts.visibility = View.GONE
            txtAddContacts.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.upside_down_arrow, 0)
        } else {
            layoutContacts.visibility = View.VISIBLE
            txtAddContacts.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.upside_down_arrow, 0)
        }
    }
}