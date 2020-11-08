package com.example.instagramchattheme.ui.custom

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.instagramchattheme.R
import com.example.instagramchattheme.util.AnimatedColor
import com.example.instagramchattheme.util.absY
import com.example.instagramchattheme.util.updateTint

class ColorThemeLayoutManager(
    private val context: Context,
    orientation: Int,
    reverseLayout: Boolean
) :
    LinearLayoutManager(context, orientation, reverseLayout) {

    private var animatedColor = AnimatedColor(
        ContextCompat.getColor(context, R.color.bg_outgoing_start),
        ContextCompat.getColor(context, R.color.bg_outgoing_end)
    )

    override fun scrollVerticallyBy(dy: Int, recycler: Recycler, state: RecyclerView.State): Int {
        val orientation = orientation
        return if (orientation == VERTICAL) {
            val scrolled = super.scrollVerticallyBy(dy, recycler, state)
            for (i in 0 until childCount) {
                getChildAt(i)?.let {
                    changeDrawableColor(it)
                }
            }
            scrolled
        } else 0
    }

    private fun changeDrawableColor(view: View) {
        view.post {
            ContextCompat.getDrawable(context, R.drawable.bg_round_corner_outgoing)
                ?.let { incomingBgDrawable ->
                    val color = animatedColor.with(getFloatRange(view))
                    incomingBgDrawable.updateTint(color)
                    view.findViewById<TextView>(R.id.tvOutgoingMessage)?.let { tv ->
                        tv.background = incomingBgDrawable
                    }
                }
        }
    }

    private fun getFloatRange(view: View): Float {
        return 1f - (view.absY() / height.toFloat())
    }

    fun updateColors(startColor: Int, endColor: Int) {
        animatedColor = AnimatedColor(startColor, endColor)
    }
}