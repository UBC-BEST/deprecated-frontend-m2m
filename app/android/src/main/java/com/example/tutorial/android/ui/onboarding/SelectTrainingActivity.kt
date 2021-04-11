package com.example.tutorial.android.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.tutorial.android.R
import com.example.tutorial.android.ui.main.HomeActivity


class SelectTrainingActivity : AppCompatActivity() {
    private lateinit var checkMarkButton: ImageButton
    private lateinit var handPalmButton: ImageButton
    private lateinit var handBalloonButton: ImageButton
    private lateinit var handPointButton: ImageButton
    private lateinit var handPinchButton: ImageButton
    private lateinit var handPhoneButton: ImageButton
    private lateinit var waveButton: ImageButton
    var trainingSelection: MutableMap<Int, Boolean> = mutableMapOf(
        R.id.hand_palm_button to false,
        R.id.hand_balloon_button to false,
        R.id.hand_point_button to false,
        R.id.hand_pinch_button to false,
        R.id.hand_phone_button to false,
        R.id.wave_button to false
    )


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

        // setting up listeners for each button

        /**
         * Asks if the user has selected all their training then submits the selected training to
         * firebase and advances to the home page.
         */
        checkMarkButton.setOnClickListener {
            val chosenTraining = trainingSelection.filter { (_, v) -> v }
            val intent = Intent(this@SelectTrainingActivity, HomeActivity::class.java)
            val bundle = Bundle()
            bundle.putString("training", chosenTraining.keys.toString())
            intent.putExtras(bundle)
            startActivity(intent)
        }

        /**
         * For the next buttons, if they are selected, then that type of training is added to the
         * list of selected training. Unclicking the button will unadd the training.
         */
        handPalmButton.setOnClickListener {
            val previousState =
                trainingSelection[R.id.hand_palm_button] ?: error("Should be a boolean!")
            trainingSelection[R.id.hand_palm_button] = !previousState
            val previousSelectionState = handPalmButton.isSelected
            handPalmButton.isSelected = !previousSelectionState
        }
        handBalloonButton.setOnClickListener {
            val previousState =
                trainingSelection[R.id.hand_balloon_button] ?: error("Should be a boolean!")
            trainingSelection[R.id.hand_balloon_button] = !previousState
            val previousSelectionState = handBalloonButton.isSelected
            handBalloonButton.isSelected = !previousSelectionState
        }
        handPointButton.setOnClickListener {
            val previousState =
                trainingSelection[R.id.hand_point_button] ?: error("Should be a boolean!")
            trainingSelection[R.id.hand_point_button] = !previousState
            val previousSelectionState = handPointButton.isSelected
            handPointButton.isSelected = !previousSelectionState
        }
        handPinchButton.setOnClickListener {
            val previousState =
                trainingSelection[R.id.hand_pinch_button] ?: error("Should be a boolean!")
            trainingSelection[R.id.hand_pinch_button] = !previousState
            val previousSelectionState = handPinchButton.isSelected
            handPinchButton.isSelected = !previousSelectionState
        }
        handPhoneButton.setOnClickListener {
            val previousState =
                trainingSelection[R.id.hand_phone_button] ?: error("Should be a boolean!")
            trainingSelection[R.id.hand_phone_button] = !previousState
            val previousSelectionState = handPhoneButton.isSelected
            handPhoneButton.isSelected = !previousSelectionState
        }
        waveButton.setOnClickListener {
            val previousState =
                trainingSelection[R.id.wave_button] ?: error("Should be a boolean!")
            trainingSelection[R.id.wave_button] = !previousState
            val previousSelectionState = waveButton.isSelected
            waveButton.isSelected = !previousSelectionState
        }
    }


    override fun onBackPressed() {
        return
    }
}