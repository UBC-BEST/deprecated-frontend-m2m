package com.example.tutorial.android.ui.main.ui.games.tapping_game

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas

class FlappyBird(var image: Bitmap) {
    private var x: Float = 0.0F
    private var y: Float = 0.0F
    private val w: Float
    private val h: Float
    private var yVelocity = 20
    private val screenWidth = Resources.getSystem().displayMetrics.widthPixels
    private val screenHeight = Resources.getSystem().displayMetrics.heightPixels
    private var scaledImg: Bitmap

    init {
        w = image.width.toFloat()
        h = image.height.toFloat()
        x = (screenWidth / 2).toFloat()
        y = (screenHeight / 2).toFloat()
        scaledImg = Bitmap.createScaledBitmap(image, (w / 2).toInt(), (h / 2).toInt(), true)
    }

    fun draw(canvas: Canvas) {
        canvas.drawBitmap(
            scaledImg,
            x - (x / 2),
            y,
            null
        )
    }

    fun update() {
        y += (yVelocity)
    }

    fun updateTouch() {
        y -= yVelocity * 2
    }

}