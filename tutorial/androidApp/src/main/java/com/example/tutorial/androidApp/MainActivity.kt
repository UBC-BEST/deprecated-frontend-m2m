package com.example.tutorial.androidApp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
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
        setContentView(R.layout.walkthrough_template)
        var imageView: ImageView = findViewById(R.id.hands_img_landing)
        var textView: TextView = findViewById(R.id.instructional_text)

        imageView.setImageResource(R.drawable.hand_balloon)
        textView.setText("Balloon")
    }
}
