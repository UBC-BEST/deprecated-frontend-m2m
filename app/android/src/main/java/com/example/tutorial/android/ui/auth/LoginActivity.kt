package com.example.tutorial.android.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tutorial.android.R
import com.example.tutorial.android.ui.auth.ui.login.AuthFragment
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
            println(e)
        }

        setContentView(R.layout.login_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.auth_container, AuthFragment.newInstance())
                .commitNow()
        }
    }
}