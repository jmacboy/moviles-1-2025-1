package com.example.practicadibujo.ui.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.practicadibujo.models.ColoredPath
import kotlin.math.abs

class FreeDrawingBoard(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private var mX: Float = 0f
    private var mY: Float = 0f
    private var path: ColoredPath = ColoredPath()
    private var pathList = mutableListOf<ColoredPath>()
    private var currentColor = Color.BLACK
    private val objPaint = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.FILL_AND_STROKE
        strokeWidth = 10f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        objPaint.color = Color.WHITE
        objPaint.style = Paint.Style.FILL_AND_STROKE

        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), objPaint)
        for (thePath in pathList) {
            thePath.draw(canvas, objPaint)
        }
        path.draw(canvas, objPaint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null) {
            return false
        }
        val x = event.x
        val y = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                touchStart(x, y)
                invalidate()
            }

            MotionEvent.ACTION_MOVE -> {
                touchMove(x, y)
                invalidate()
            }

            MotionEvent.ACTION_UP -> {
                touchEnd(x, y)
                invalidate()
            }
        }
        return true
    }

    private fun touchEnd(x: Float, y: Float) {
        path.path.lineTo(mX, mY)
        path.path.moveTo(x, y)
        pathList.add(path)

    }

    private fun touchMove(x: Float, y: Float) {
        val dx = abs(x - mX)
        val dy = abs(y - mY)
        if (dx >= 4f || dy >= 4f) {
            path.path.quadTo(
                mX, mY, (x + mX) / 2,
                (y + mY) / 2
            )
            mX = x
            mY = y
        }
    }

    private fun touchStart(x: Float, y: Float) {
        path = ColoredPath()
        path.color = currentColor
        path.path.moveTo(x, y)
        mX = x
        mY = y
    }

    fun erase() {
        currentColor = Color.WHITE
    }

    fun draw() {
        currentColor = Color.BLACK
    }
}