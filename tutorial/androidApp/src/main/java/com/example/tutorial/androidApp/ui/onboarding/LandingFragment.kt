package com.example.tutorial.androidApp.ui.onboarding


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.tutorial.androidApp.R


class LandingFragment : AppCompatActivity() {
    private lateinit var landingLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
            println(e)
        }

        setContentView(R.layout.fragment_landing)
        landingLayout = findViewById(R.id.landing)
        landingLayout.setOnClickListener {
            val intent = Intent(this@LandingFragment, WalkThroughActivity::class.java)
            startActivity(intent)
        }
    }
}