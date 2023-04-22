package com.example.togetherapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UserProfile : AppCompatActivity() {

    private lateinit var frameLayout: FrameLayout
    private lateinit var txtName: TextView
    private lateinit var txtEmail: TextView
    private lateinit var mAuth: FirebaseAuth
    private lateinit var edtContact1: EditText
    private lateinit var edtContact2: EditText
    private lateinit var btnSave: Button
    private lateinit var layoutContacts: LinearLayout
    private lateinit var database: FirebaseDatabase
    private lateinit var ref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        mAuth = FirebaseAuth.getInstance()

        frameLayout = findViewById(R.id.frame)
        txtName = findViewById(R.id.txt_name)
        txtEmail = findViewById(R.id.txt_email)
        edtContact1 = findViewById(R.id.edt_contact1)
        edtContact2 = findViewById(R.id.edt_contact2)
        btnSave = findViewById(R.id.btnSave)
        layoutContacts = findViewById(R.id.layout_contacts)

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
        // Set click listener for "Save" button
        btnSave.setOnClickListener {
            onSaveClicked()
        }

        // Get Firebase database reference
        database = FirebaseDatabase.getInstance()
        ref = database.getReference("contacts")

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
            txtAddContacts.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.`arrow_down`, 0)
        } else {
            layoutContacts.visibility = View.VISIBLE
            txtAddContacts.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.`arrow_up`, 0)
        }
    }
    // Click event handler for "Save" button
    fun onSaveClicked() {
        val contact1 = edtContact1.text.toString().trim()
        val contact2 = edtContact2.text.toString().trim()

        // Check if both contact fields are not empty
        if (contact1.isNotEmpty() && contact2.isNotEmpty()) {
            // Save contact1 and contact2 to Firebase under "contacts" node
            val contactsRef = ref.child("contacts")
            contactsRef.child("contact1").setValue(contact1)
            contactsRef.child("contact2").setValue(contact2)

            // Display saved contacts as non-editing texts
            edtContact1.isEnabled = false
            edtContact1.isFocusable = false
            edtContact2.isEnabled = false
            edtContact2.isFocusable = false

            Toast.makeText(this, "Contacts saved successfully", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Please enter both contacts", Toast.LENGTH_SHORT).show()
        }
    }
}