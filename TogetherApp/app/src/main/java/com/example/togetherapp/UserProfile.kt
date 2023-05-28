package com.example.togetherapp

import BaseActivity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class UserProfile : BaseActivity() {

    private lateinit var frameLayout: FrameLayout
    private lateinit var txtName: TextView
    private lateinit var txtEmail: TextView
    private lateinit var mAuth: FirebaseAuth
    private lateinit var edtContact1: EditText
    private lateinit var edtContact2: EditText
    private lateinit var edtContact3: EditText
    private lateinit var edtContact4: EditText
    private lateinit var edtContact5: EditText
    private lateinit var btnSave: Button
    private lateinit var btnEdit: Button
    private lateinit var btnLogout: Button
    private lateinit var layoutContacts: LinearLayout
    private lateinit var database: FirebaseDatabase
    private lateinit var ref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        //home button action
        val homeButton = findViewById<ImageButton>(R.id.homebutton)
        homeButton.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
            finish()
        }

        mAuth = FirebaseAuth.getInstance()

        frameLayout = findViewById(R.id.frame)
        txtName = findViewById(R.id.txt_name)
        txtEmail = findViewById(R.id.txt_email)
        edtContact1 = findViewById(R.id.edt_contact1)
        edtContact2 = findViewById(R.id.edt_contact2)
        edtContact3 = findViewById(R.id.edt_contact3)
        edtContact4 = findViewById(R.id.edt_contact4)
        edtContact5 = findViewById(R.id.edt_contact5)
        btnSave = findViewById(R.id.btnSave)
        btnEdit = findViewById(R.id.buttonEdit)
        btnLogout = findViewById(R.id.btnLogout)
        layoutContacts = findViewById(R.id.layout_contacts)

       // val btnLogout = findViewById<Button>(R.id.btnLogout)
        // Get the currently logged in user
        val currentUser = mAuth.currentUser

        if (currentUser != null) {
            // Set the name, email, and password in the TextViews
            txtName.text = "Name: ${currentUser.displayName}"
            txtEmail.text = "Email: ${currentUser.email}"
        }
        layoutContacts.visibility = View.GONE


        // Set click listener for "Add contacts" button
        val txtAddContacts = findViewById<TextView>(R.id.txt_add_contacts)
        txtAddContacts.setOnClickListener {
            onAddContactsClicked(it)
        }
        val database = Firebase.database
        val myRef = database.getReference("contacts")

        val postRef = myRef.child(mAuth.uid.toString())

        postRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    edtContact1.setText(snapshot.children.elementAt(0).value.toString())
                    edtContact2.setText(snapshot.children.elementAt(1).value.toString())
                    edtContact3.setText(snapshot.children.elementAt(2).value.toString())
                    edtContact4.setText(snapshot.children.elementAt(3).value.toString())
                    edtContact5.setText(snapshot.children.elementAt(4).value.toString())

                    edtContact1.isEnabled = false
                    edtContact2.isEnabled = false
                    edtContact3.isEnabled = false
                    edtContact4.isEnabled = false
                    edtContact5.isEnabled = false

                    btnEdit.isEnabled=true
                    btnEdit.visibility = View.VISIBLE
                    btnSave.isEnabled =false
                    btnSave.visibility = View.INVISIBLE


                }else{
                    edtContact1.isEnabled = true
                    edtContact2.isEnabled = true
                    edtContact3.isEnabled = true
                    edtContact4.isEnabled = true
                    edtContact5.isEnabled = true

                    btnSave.isEnabled =true
                    btnSave.visibility= View.VISIBLE
                    btnEdit.isEnabled=false
                    btnEdit.visibility=View.INVISIBLE

                }
            }

            override fun onCancelled(error: DatabaseError) {
                //Toast.makeText(applicationContext, "Database Error", Toast.LENGTH_SHORT).show()
                if (mAuth.currentUser != null) {
                    Toast.makeText(applicationContext, "Database Error", Toast.LENGTH_SHORT).show()
                }
            }
        })

        // Set click listener for "Save" button
        btnSave.setOnClickListener {
            onSaveClicked( myRef)
            btnEdit.isEnabled=true
            btnEdit.visibility = View.VISIBLE
            btnSave.isEnabled =false
            btnSave.visibility = View.INVISIBLE
        }
        btnEdit.setOnClickListener {


            onEditClicked(myRef)
        }

        btnLogout.setOnClickListener {
            // Log out the user
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, LogIn::class.java)
            startActivity(intent)
            finish() // Finish the current activity to prevent going back to the user profile page
        }

    }

    fun onEditClicked(myRef: DatabaseReference) {

        // Set the value of the unique key to the contacts object
        myRef.child(mAuth.uid.toString()).removeValue()
        edtContact1.isEnabled = true
        edtContact2.isEnabled = true
        edtContact3.isEnabled = true
        edtContact4.isEnabled = true
        edtContact5.isEnabled = true

        btnSave.isEnabled =true
        btnSave.visibility= View.VISIBLE
        btnEdit.isEnabled=false
        btnEdit.visibility=View.INVISIBLE

    }

    // Click event handler for "Add contacts" button
    fun onAddContactsClicked(view: View) {
        val layoutContacts = findViewById<LinearLayout>(R.id.layout_contacts)
        val txtAddContacts = findViewById<TextView>(R.id.txt_add_contacts)

        // Toggle visibility of contact input fields
        if (layoutContacts.visibility == View.VISIBLE) {
            layoutContacts.visibility = View.GONE
            btnLogout.visibility = View.VISIBLE
            btnEdit.visibility=View.INVISIBLE
            txtAddContacts.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.`arrow_down`, 0)
        } else {
            layoutContacts.visibility = View.VISIBLE
            btnLogout.visibility = View.INVISIBLE
            btnEdit.visibility=View.VISIBLE
            txtAddContacts.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.`arrow_up`, 0)
        }
    }
    // Click event handler for "Save" button
    fun onSaveClicked(myRef: DatabaseReference) {
        val contact1 = edtContact1.text.toString().trim()
        val contact2 = edtContact2.text.toString().trim()
        val contact3 = edtContact3.text.toString().trim()
        val contact4 = edtContact4.text.toString().trim()
        val contact5 = edtContact5.text.toString().trim()

        // Check if both contact fields are not empty
        if (contact1.isNotEmpty() && contact2.isNotEmpty() && contact3.isNotEmpty() && contact4.isNotEmpty() && contact5.isNotEmpty()) {

            val contacts = Contacts(contact1, contact2, contact3, contact4, contact5)
            // Set the value of the unique key to the contacts object
            myRef.child(mAuth.uid.toString()).setValue(contacts)


            edtContact1.isEnabled = false
            edtContact2.isEnabled = false
            edtContact3.isEnabled = false
            edtContact4.isEnabled = false
            edtContact5.isEnabled = false


            Toast.makeText(this, "Contacts saved successfully", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Please enter all 5 contacts", Toast.LENGTH_SHORT).show()
        }
    }
}