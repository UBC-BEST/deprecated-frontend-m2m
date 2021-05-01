package com.example.tutorial.android.ui.main.ui.games.tapping_game

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.example.tutorial.android.R
import com.example.tutorial.android.ui.main.ui.games.Background


class FlappyGameView(context: Context?, attrs: AttributeSet?) : SurfaceView(context, attrs),
    SurfaceHolder.Callback {
    private val thread: FlappyGameThread
    private var bird: FlappyBird? = null
    private var background: Background? = null

    private var touched: Boolean = false
    private var touchX: Int = 0
    private var touchY: Int = 0

    init {
        holder.addCallback(this)
        thread = FlappyGameThread(holder, this)
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        // background
        background =
            Background(BitmapFactory.decodeResource(resources, R.drawable.cleaner_bg_small))

        // game object
        bird = FlappyBird(BitmapFactory.decodeResource(resources, R.drawable.birb))
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

    fun update() {
        bird!!.update()
        if (touched) {
            bird!!.updateTouch()
        }
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
        if (canvas != null) {
            background!!.draw(canvas)
            bird!!.draw(canvas)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        // whenever there is touch, get position

        touchX = event?.x!!.toInt()
        touchY = event.y.toInt()

        when (event.action) {
            MotionEvent.ACTION_DOWN -> touched = true
            MotionEvent.ACTION_MOVE -> touched = true
            MotionEvent.ACTION_UP -> touched = false
            MotionEvent.ACTION_CANCEL -> touched = false
            MotionEvent.ACTION_OUTSIDE -> touched = false
        }
        return true
    }
}
