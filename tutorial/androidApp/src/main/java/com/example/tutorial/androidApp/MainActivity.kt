package com.example.tutorial.androidApp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tutorial.androidApp.ui.onboarding.LandingActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this@MainActivity, LandingActivity::class.java))
    }
}
