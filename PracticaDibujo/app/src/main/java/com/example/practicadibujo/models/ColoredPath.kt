package com.example.practicadibujo.models


import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path

class ColoredPath {
    var color: Int = Color.BLACK
    var path: Path = Path()

    fun draw(canvas: Canvas, objPaint: Paint) {
        objPaint.color = color
        if (color == Color.WHITE) {
            objPaint.strokeWidth = 30f
        } else {
            objPaint.strokeWidth = 10f
        }
        objPaint.style = Paint.Style.STROKE
        canvas.drawPath(path, objPaint)
    }

}