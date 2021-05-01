package com.example.tutorial.android.ui.main.ui.games

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas

open class Floatable(private val image: Bitmap) {
    private var x: Float = 0.0F
    private var y: Float = 0.0F
    private val w: Float
    private val h: Float
    private val screenWidth = Resources.getSystem().displayMetrics.widthPixels
    private val screenHeight = Resources.getSystem().displayMetrics.heightPixels

    init {
        w = image.width.toFloat()
        h = image.height.toFloat()

        x = screenWidth.toFloat()
        y = screenHeight.toFloat()
    }

    fun draw(canvas: Canvas) {
        canvas.drawBitmap(
            Bitmap.createScaledBitmap(image, (w / 2).toInt(), (h / 2).toInt(), true),
            x,
            y,
            null
        )
    }
}