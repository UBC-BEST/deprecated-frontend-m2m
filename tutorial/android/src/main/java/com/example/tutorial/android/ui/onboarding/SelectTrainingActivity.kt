package com.example.tutorial.android.ui.onboarding

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.tutorial.android.R


class SelectTrainingActivity : AppCompatActivity() {
    private lateinit var checkMarkButton: ImageButton
    private lateinit var handPalmButton: ImageButton
    private lateinit var handBalloonButton: ImageButton
    private lateinit var handPointButton: ImageButton
    private lateinit var handPinchButton: ImageButton
    private lateinit var handPhoneButton: ImageButton
    private lateinit var waveButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
            println(e)
        }
        setContentView(R.layout.fragment_select_focus)

        // initializing all the buttons
        checkMarkButton = findViewById(R.id.checkmark)
        handPalmButton = findViewById(R.id.hand_palm_button)
        handBalloonButton = findViewById(R.id.hand_balloon_button)
        handPointButton = findViewById(R.id.hand_point_button)
        handPinchButton = findViewById(R.id.hand_pinch_button)
        handPhoneButton = findViewById(R.id.hand_phone_button)
        waveButton = findViewById(R.id.wave_button)

        // if any of these buttons are clicked, add training to user
    }

    override fun onBackPressed() {
        return
    }
}