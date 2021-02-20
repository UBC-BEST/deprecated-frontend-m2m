package com.example.tutorial.androidApp.ui.onboarding


import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.tutorial.androidApp.R


class LandingFragment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_landing)
        // add random time for now
        Handler().postDelayed({
            setContentView(R.layout.fragment_complete_exercises)
        }, 20)
    }
}