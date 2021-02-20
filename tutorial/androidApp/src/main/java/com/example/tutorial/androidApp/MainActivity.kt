package com.example.tutorial.androidApp

import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.tutorial.androidApp.ui.onboarding.WalkThrough
import com.example.tutorial.shared.Greeting

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    private lateinit var handBalloonButton: ImageButton
    private lateinit var handPhoneButton: ImageButton
    private lateinit var handsPinchButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
            println(e)
        }

        setContentView(R.layout.fragment_landing)

        Handler().postDelayed({
            setContentView(R.layout.fragment_complete_exercises)
            handsPinchButton = findViewById(R.id.button_hands_pinch)

            handsPinchButton.setOnClickListener {
                setContentView(R.layout.fragment_play_interactive_games)
                handBalloonButton = findViewById(R.id.button_hands_balloon)

                handBalloonButton.setOnClickListener {
                    setContentView(R.layout.fragment_track_progress)
                    handPhoneButton = findViewById(R.id.button_hands_phone)
                    handPhoneButton.setOnClickListener { setContentView(R.layout.fragment_auth) }
                }
            }
        }, 600)
    }
}
