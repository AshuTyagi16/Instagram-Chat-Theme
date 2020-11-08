package com.example.instagramchattheme.util

import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView


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

inline fun <reified T : RecyclerView.ViewHolder> RecyclerView.forEachVisibleHolder(
    action: (T) -> Unit
) {
    for (i in 0 until childCount) {
        action(getChildViewHolder(getChildAt(i)) as T)
    }
}