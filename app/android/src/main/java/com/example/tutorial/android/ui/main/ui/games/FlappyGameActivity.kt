package com.example.tutorial.android.ui.main.ui.games

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tutorial.android.R

class FlappyGameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
            println(e)
        }
        setContentView(R.layout.activity_flappy_game)
    }
}