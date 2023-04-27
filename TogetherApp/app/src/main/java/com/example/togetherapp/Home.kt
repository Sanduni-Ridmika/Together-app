package com.example.togetherapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton
import android.widget.PopupMenu
import com.google.firebase.auth.FirebaseAuth

class Home : AppCompatActivity() {
    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button
    private lateinit var hamburgerButton: ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {
            val intent = Intent(this, Questionnaire::class.java)
            startActivity(intent)
        }

        button2 = findViewById(R.id.button2)
        button2.setOnClickListener {
            val intent = Intent(this, Information::class.java)
            startActivity(intent)
        }

        button3 = findViewById(R.id.button3)
        button3.setOnClickListener {
            val intent = Intent(this, Meditations::class.java)
            startActivity(intent)
        }

        button4 = findViewById(R.id.button4)
        button4.setOnClickListener {
            val intent = Intent(this, SupportGroup::class.java)
            startActivity(intent)
        }
        // Find and setup the hamburger button
        hamburgerButton = findViewById(R.id.hamburgerButton)
        hamburgerButton.setOnClickListener { showPopupMenu() }
    }
    // Function to show the popup menu
    private fun showPopupMenu() {
        val popupMenu = PopupMenu(this, hamburgerButton)
        popupMenu.menuInflater.inflate(R.menu.hamburger_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_profile -> {
                    // Open the profile activity
                    val intent = Intent(this, UserProfile::class.java)
                    startActivity(intent)
                    true
                }
                R.id.menu_logout -> {
                    // Perform logout action
                    performLogout()
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }

    // Function to perform the logout action
    private fun performLogout() {
        // Log out the user
        FirebaseAuth.getInstance().signOut()
        val intent = Intent(this, LogIn::class.java)
        startActivity(intent)
        finish()
    }
}