package com.example.togetherapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton
import android.widget.PopupMenu

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

        hamburgerButton = findViewById(R.id.hamburgerButton)
        hamburgerButton.setOnClickListener {
            // Handle hamburgerButton click
            // showPopupMenu()
        }
    }
    /* private fun showPopupMenu() {
        // Create a PopupMenu for overflow options
         val popupMenu = PopupMenu(this, hamburgerButton)
         popupMenu.menuInflater.inflate(R.menu.overflow_menu, popupMenu.menu)
          popupMenu.setOnMenuItemClickListener { item: MenuItem ->
             when (item.itemId) {
                 R.id.menu_profile -> {
                     // Handle Profile option
                     // Start the ProfileActivity
                     val intent = Intent(this, UserProfile::class.java)
                     startActivity(intent)
                     true
                 }
                 R.id.menu_logout -> {
                     // Handle Logout option
                     // Perform logout operation
                     // For example: clear session, remove tokens, etc.
                     // Then start the LoginActivity
                     val intent = Intent(this, LogIn ::class.java)
                     intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                     startActivity(intent)
                     finish()
                     true
                 }
                 else -> false
             }
         }
         popupMenu.show()
     } */
}