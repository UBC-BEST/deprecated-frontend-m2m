package com.example.tutorial.android.ui.onboarding


import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.tutorial.android.R


class LandingActivity : AppCompatActivity() {
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
            val intent = Intent(this@LandingActivity, WalkThroughActivity::class.java)
            startActivity(intent)
        }
    }
}