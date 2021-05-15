package com.example.tutorial.android.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.tutorial.android.R
import com.example.tutorial.android.ui.auth.AuthActivity


class WalkThroughActivity : AppCompatActivity() {
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

        setContentView(R.layout.fragment_complete_exercises)
        handsPinchButton = findViewById(R.id.button_hands_pinch)

        handsPinchButton.setOnClickListener {
            setContentView(R.layout.fragment_play_interactive_games)
            handBalloonButton = findViewById(R.id.button_hands_balloon)

            handBalloonButton.setOnClickListener {
                setContentView(R.layout.fragment_track_progress)
                handPhoneButton = findViewById(R.id.button_hands_phone)
                handPhoneButton.setOnClickListener {
                    startActivity(
                        Intent(
                            this@WalkThroughActivity, AuthActivity::class.java
                        )
                    )
                }
            }
        }
    }
}
