package com.example.tutorial.androidApp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.tutorial.androidApp.ui.onboarding.LandingFragment
import com.example.tutorial.androidApp.ui.onboarding.WalkThroughActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this@MainActivity, LandingFragment::class.java))
    }
}
 