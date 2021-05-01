package com.example.tutorial.android.ui.main.ui.games.tapping_game

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView

//java.lang.IllegalStateException: You need to use a Theme.AppCompat theme (or descendant) with this activity.

class TappingGameView(context: Context?, attrs: AttributeSet?) : SurfaceView(context, attrs),
    SurfaceHolder.Callback {
    private val thread: TappingGameThread

    init {
        holder.addCallback(this)
        thread = TappingGameThread(holder, this)
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        TODO("Not yet implemented")
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        // start game thread
        thread.setRunning(true)
        thread.start()

    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        var retry = true
        while (retry) {
            try {
                thread.setRunning(false)
                thread.join()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            retry = false
        }
    }

    fun update() {}

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
    }
}
