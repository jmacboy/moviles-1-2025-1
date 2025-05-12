package com.example.practicaanimacion.ui.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.practicaanimacion.models.Direction
import com.example.practicaanimacion.models.Square
import com.example.practicaanimacion.observer.Observer

class AnimationBoard(context: Context?, attrs: AttributeSet?) :
    View(context, attrs), Observer {

    private var square = Square(100f, 100f).apply {
        addObserver(this@AnimationBoard)
    }
    private val paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
        color = android.graphics.Color.BLACK
        strokeWidth = 10f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        square.changeScreenSize(width, height)
        square.draw(canvas, paint)
    }

    fun moveUp() {
        square.animate(Direction.UP)
    }

    fun moveDown() {
        square.animate(Direction.DOWN)
    }

    fun moveLeft() {
        square.animate(Direction.LEFT)
    }

    fun moveRight() {
        square.animate(Direction.RIGHT)
    }

    override fun update() {
        invalidate()
    }
}