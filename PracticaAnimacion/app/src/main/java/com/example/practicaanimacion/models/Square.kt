package com.example.practicaanimacion.models

import android.graphics.Canvas
import android.graphics.Paint
import com.example.practicaanimacion.observer.Observable
import com.example.practicaanimacion.observer.Observer

class Square(private var x: Float, private var y: Float) : Observable {
    private var observer: Observer? = null
    private var direction: Direction = Direction.NONE
    private var isMoving: Boolean = false
    private var screenHeight: Int = 0
    private var screenWidth: Int = 0

    fun draw(canvas: Canvas, paint: Paint) {
        canvas.drawRect(x, y, x + 100f, y + 100f, paint)
    }

    private fun changeDirection(direction: Direction) {
        this.isMoving = true
        this.direction = direction
    }

    private fun move() {
        when (direction) {
            Direction.UP -> {
                if (y < 0) {
                    isMoving = false
                    return
                }
                y -= MOVEMENT_SPEED
            }

            Direction.DOWN -> {
                if (y + SQUARE_HEIGHT > screenHeight) {
                    isMoving = false
                    return
                }
                y += MOVEMENT_SPEED
            }

            Direction.LEFT -> {
                if (x < 0) {
                    isMoving = false
                    return
                }
                x -= MOVEMENT_SPEED
            }

            Direction.RIGHT -> {
                if (x + SQUARE_WIDTH > screenWidth) {
                    isMoving = false
                    return
                }
                x += MOVEMENT_SPEED
            }

            Direction.NONE -> {}
        }
    }

    fun changeScreenSize(width: Int, height: Int) {
        this.screenWidth = width
        this.screenHeight = height
    }

    private fun stopMoving() {
        this.isMoving = false
    }

    fun animate(direction: Direction) {
        Thread {
            this.stopMoving()
            Thread.sleep(ANIMATION_SPEED * 2)
            this.changeDirection(direction)
            while (isMoving) {
                this.move()
                Thread.sleep(ANIMATION_SPEED)
                notifyObservers()
            }
        }.start()
    }


    override fun addObserver(observer: Observer) {
        this.observer = observer
    }

    override fun notifyObservers() {
        this.observer?.update()
    }

    companion object {
        const val SQUARE_WIDTH = 100f
        const val SQUARE_HEIGHT = 100f
        const val MOVEMENT_SPEED = 10f
        const val ANIMATION_SPEED = 50L
    }
}