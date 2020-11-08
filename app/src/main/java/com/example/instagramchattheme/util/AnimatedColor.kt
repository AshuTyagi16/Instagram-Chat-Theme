package com.example.instagramchattheme.util

import android.graphics.Color

class AnimatedColor(private val startColor: Int, private val endColor: Int) {

    private val startHSV: FloatArray
    private val endHSV: FloatArray
    private val move = FloatArray(3)


    init {
        startHSV = toHSV(startColor)
        endHSV = toHSV(endColor)
    }

    fun with(delta: Float): Int {
        if (delta <= 0) return startColor
        return if (delta >= 1) endColor else Color.HSVToColor(move(delta))
    }

    private fun move(delta: Float): FloatArray {
        move[0] = (endHSV[0] - startHSV[0]) * delta + startHSV[0]
        move[1] = (endHSV[1] - startHSV[1]) * delta + startHSV[1]
        move[2] = (endHSV[2] - startHSV[2]) * delta + startHSV[2]
        return move
    }

    private fun toHSV(color: Int): FloatArray {
        val hsv = FloatArray(3)
        Color.colorToHSV(color, hsv)
        return hsv
    }
}