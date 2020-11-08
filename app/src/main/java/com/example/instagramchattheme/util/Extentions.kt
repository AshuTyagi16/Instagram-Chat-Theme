package com.example.instagramchattheme.util

import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.graphics.drawable.DrawableCompat


fun View.absY(): Float {
    val location = IntArray(2)
    getLocationOnScreen(location)
    return (location[1].toFloat() - height.toFloat())
}

fun Drawable.updateTint(color: Int) {
    DrawableCompat.wrap(this)?.let {
        DrawableCompat.setTint(it, color)
    }
}

fun Int.dpToPx(): Int {
    return this * Resources.getSystem().displayMetrics.density.toInt()
}