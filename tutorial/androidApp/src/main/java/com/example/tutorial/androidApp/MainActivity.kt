package com.example.tutorial.androidApp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tutorial.shared.Greeting

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }
        setContentView(R.layout.fragment_landing)
    }
}
