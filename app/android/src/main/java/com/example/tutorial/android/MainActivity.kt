package com.example.tutorial.android

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.tutorial.android.ui.main.HomeActivity
import com.example.tutorial.android.ui.onboarding.LandingActivity
import com.example.tutorial.shared.network.M2MApi
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var backendApi: M2MApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()
        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) {
            Log.d("CURRENT USER", "There is a user logged in.")

            startActivity(Intent(this@MainActivity, HomeActivity::class.java))
        } else {
            Log.d("CURRENT USER", "There is no user logged in.")
            startActivity(Intent(this@MainActivity, LandingActivity::class.java))
        }
    }
}
