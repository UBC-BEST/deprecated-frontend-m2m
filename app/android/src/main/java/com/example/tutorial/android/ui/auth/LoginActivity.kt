package com.example.tutorial.android.ui.auth

import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.tutorial.android.R
import com.example.tutorial.android.ui.auth.ui.login.AuthFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
            println(e)
        }
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signInWithEmailAndPassword("test@test.com", "password").addOnCompleteListener {
            if (it.isSuccessful) {
                Log.d("DEBUG", "signInWithEmailAndPassword: Successful")
            } else {
                Log.w("WARN", it.exception)
            }
        }

        firebaseAuth.createUserWithEmailAndPassword("test@test.com", "password")
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d("DEBUG", "createUserWithEmailAndPassword: Successful")
                } else {
                    Log.w("WARN", it.exception)
                }
            }

        setContentView(R.layout.login_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.auth_container, AuthFragment.newInstance())
                .commitNow()
        }
    }
}