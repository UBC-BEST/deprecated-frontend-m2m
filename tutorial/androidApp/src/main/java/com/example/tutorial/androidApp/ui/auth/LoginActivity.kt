package com.example.tutorial.androidApp.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tutorial.androidApp.R
import com.example.tutorial.androidApp.ui.auth.ui.login.AuthFragment

class LoginActivity : AppCompatActivity() {

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