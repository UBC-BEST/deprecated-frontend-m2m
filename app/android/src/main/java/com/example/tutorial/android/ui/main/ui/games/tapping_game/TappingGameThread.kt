package com.example.tutorial.android.ui.main.ui.games.tapping_game

import android.graphics.Canvas
import android.view.SurfaceHolder

class TappingGameThread(
    private val surfaceHolder: SurfaceHolder,
    private val tappingGameView: TappingGameView
) : Thread() {
    private var running: Boolean = false
    private val targetFPS = 50

    fun setRunning(isRunning: Boolean) {
        this.running = isRunning
    }

    override fun run() {
        var startTime: Long
        var timeMillis: Long
        var waitTime: Long
        val targetTime = (1000 / targetFPS).toLong()

        while (running) {
            startTime = System.nanoTime()
            canvas = null
            try {
                // lock canvas so we can draw on it
                // use of synchronized locks does not let any threads modify to surfaceHGolder
                // update game state
                // unlock in finally block
                canvas = this.surfaceHolder.lockCanvas()
                synchronized(surfaceHolder) {
                    this.tappingGameView.update()
                    this.tappingGameView.draw(canvas!!)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
            timeMillis = (System.nanoTime() - startTime) / 1000000
            waitTime = targetTime - timeMillis

            try {
                sleep(waitTime)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    companion object {
        private var canvas: Canvas? = null
    }
}