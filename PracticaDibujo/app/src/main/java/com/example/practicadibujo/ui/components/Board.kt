package com.example.practicadibujo.ui.components

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.practicadibujo.models.ShapeType
import kotlin.math.sqrt
import androidx.core.graphics.createBitmap


class Board(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private var xStart = 0f
    private var yStart = 0f
    private var xEnd = 0f
    private var yEnd = 0f
    private val paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
        color = Color.BLACK
        strokeWidth = 10f
    }
    private var bufferBitmap: Bitmap = createBitmap(800, 800)
    private var bufferCanvas: Canvas = Canvas(bufferBitmap)
    private var shapeType: ShapeType = ShapeType.NONE

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        bufferBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        bufferCanvas = Canvas(bufferBitmap);
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bufferBitmap, 0f, 0f, null)
        drawShape(canvas)
    }
    fun drawShape(canvas: Canvas){
        if (shapeType == ShapeType.NONE) return
        when (shapeType) {
            ShapeType.LINE -> canvas.drawLine(xStart, yStart, xEnd, yEnd, paint)
            ShapeType.SQUARE -> canvas.drawRect(xStart, yStart, xEnd, yEnd, paint)
            ShapeType.CIRCLE -> {
                val radius = sqrt(
                    ((xEnd - xStart) * (xEnd - xStart) + (yEnd - yStart) * (yEnd - yStart)).toDouble()
                ).toFloat() / 2
                canvas.drawCircle(xStart, yStart, radius, paint)
            }
            ShapeType.NONE -> {}
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null) return false
        val x = event.x
        val y = event.y
        if (event.action == MotionEvent.ACTION_DOWN) {
            xStart = x
            yStart = y
            xEnd = x
            yEnd = y
            invalidate()
        }
        if (event.action == MotionEvent.ACTION_UP) {
            xEnd = x
            yEnd = y
            drawShape(bufferCanvas)

            invalidate()
        }
        if (event.action == MotionEvent.ACTION_MOVE) {
            xEnd = x
            yEnd = y
            invalidate()
        }
        return true
    }

    fun setShape(type: ShapeType) {
        shapeType = type
        when (type) {
            ShapeType.LINE -> paint.color = Color.RED
            ShapeType.SQUARE -> paint.color = Color.GREEN
            ShapeType.CIRCLE -> paint.color = Color.BLUE
            ShapeType.NONE -> paint.color = Color.BLACK
        }
    }

}