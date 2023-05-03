package com.example.togetherapp

import BaseActivity
import android.content.Intent
import android.os.Bundle
import android.telephony.SmsManager
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import android.Manifest

import android.app.PendingIntent
import android.content.pm.PackageManager

import android.location.Location
import android.location.LocationListener
import android.location.LocationManager

import android.widget.Button

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class SupportGroup : BaseActivity() {
    private lateinit var text: TextView
    private var c1:String = ""
    private var c2:String = ""
    private lateinit var btnAskForHelp: Button

    private var locationManager : LocationManager? = null
    private var mylocation_long =""
    private var mylocation_lati =""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_support_group)
        val p =  arrayOf(Manifest.permission.SEND_SMS,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION)
        ActivityCompat.requestPermissions(this,p,1);
        text = findViewById(R.id.text)
        btnAskForHelp = findViewById(R.id.button)


        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager?





        val locationListener: LocationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                mylocation_long = location.longitude.toString()
                mylocation_lati = location.latitude.toString()

            }
            override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
            override fun onProviderEnabled(provider: String) {}
            override fun onProviderDisabled(provider: String) {}
        }

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            return
        }
        locationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener)

        text.setOnClickListener {
            val intent = Intent(this, UserProfile::class.java)
            startActivity(intent)

        }


        btnAskForHelp.setOnClickListener {


            var mAuth = FirebaseAuth.getInstance()
            val database = Firebase.database
            val myRef = database.getReference("contacts")
            myRef.child(mAuth.uid.toString()).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {

                        onAskForHelpClicked(mylocation_long,mylocation_lati)

                    } else {
                        Toast.makeText(applicationContext, "Please add contacts to your support group", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(applicationContext, "Database Error", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }



    private fun onAskForHelpClicked(longi :String,lati:String) {
        var mAuth = FirebaseAuth.getInstance()

        val database = Firebase.database
        val myRef = database.getReference("contacts")

        myRef.child(mAuth.uid.toString())
        var cont1 =  myRef.child(mAuth.uid.toString()).child("contact1").get().addOnSuccessListener {
            Log.i("firebase", "Got value ${it.value} "+"http//www.google.com/maps/place/"+longi+","+lati)
            c1 = "${it.value}"
            sendSMS(c1,"ALERT Hello! I need help, I am at "+"http//www.google.com/maps/place/"+lati+","+longi)
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }
        var cont2 =  myRef.child(mAuth.uid.toString()).child("contact2").get().addOnSuccessListener {
            Log.i("firebase", "Got value ${it.value} ")
            c2 = "${it.value}"
            sendSMS(c2,"ALERT Hello! I need help, I am at "+"http//www.google.com/maps/place/"+lati+","+longi)

        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }

    }

    private fun sendSMS(phoneNumber: String, message: String) {
        val sentPI: PendingIntent = PendingIntent.getBroadcast(
            this,
            0,
            Intent("SMS_SENT"),
            PendingIntent.FLAG_IMMUTABLE
        )
        SmsManager.getDefault().sendTextMessage(phoneNumber, null, message, sentPI, null)
    }

}