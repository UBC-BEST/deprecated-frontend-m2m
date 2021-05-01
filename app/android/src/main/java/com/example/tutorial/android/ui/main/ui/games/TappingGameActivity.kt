package com.example.tutorial.android.ui.main.ui.games

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tutorial.android.R

class TappingGameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
            println(e)
        }
        setContentView(R.layout.activity_tapping_game)
    }
}

//followed this tutorial https://www.tutorialkart.com/kotlin-android/get-started-with-android-game-development/