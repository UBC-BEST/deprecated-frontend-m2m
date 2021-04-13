package com.example.tutorial.android.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tutorial.android.R
import com.example.tutorial.android.ui.auth.ui.login.LoginFragment

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
            println(e)
        }

        setContentView(R.layout.activity_login)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.login_container, LoginFragment.newInstance())
                .commitNow()
        }
    }
}