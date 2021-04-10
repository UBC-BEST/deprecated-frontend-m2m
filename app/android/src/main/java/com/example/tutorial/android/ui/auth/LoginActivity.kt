package com.example.tutorial.android.ui.auth

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tutorial.android.R
import com.example.tutorial.android.ui.auth.ui.login.AuthFragment

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
            println(e)
        }

        setContentView(R.layout.login_activity)
//        val changeAuth: TextView = findViewById(R.id.change_authentication)
//        changeAuth.setOnClickListener {
//
//        }
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.login_container, AuthFragment.newInstance())
                .commitNow()
        }
    }
}