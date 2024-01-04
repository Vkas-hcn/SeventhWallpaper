package com.serven.pet.seventhwallpaper.set
import android.content.Context
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.serven.pet.seventhwallpaper.R

import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CircleIndicator(
    private val context: Context,
    private val indicatorLayout: LinearLayout,
    private val recyclerView: RecyclerView
) {
    private var dotSize: Int = 16 // 小圆点的大小
    private var dotSpacing: Int = 8 // 小圆点之间的间隔
    private var dotOffDrawable: Drawable? = ContextCompat.getDrawable(context, R.drawable.ic_an_y)
    private var dotOnDrawable: Drawable? = ContextCompat.getDrawable(context, R.drawable.ic_l_y)

    fun createDotPanel(count: Int) {
        indicatorLayout.removeAllViews()
        for (i in 0 until count) {
            val dot = ImageView(context)
            val params = LinearLayout.LayoutParams(dotSize, dotSize)
            params.setMargins(dotSpacing, 0, dotSpacing, 0)
            dot.layoutParams = params
            dot.setImageDrawable(if (i == 0) dotOnDrawable else dotOffDrawable)
            indicatorLayout.addView(dot)
        }

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as? LinearLayoutManager
                val position = layoutManager?.findFirstCompletelyVisibleItemPosition() ?: 0
                updateIndicator(position % count)
            }
        })
    }

    fun updateIndicator(selectedPosition: Int) {
        for (i in 0 until indicatorLayout.childCount) {
            val dot = indicatorLayout.getChildAt(i) as ImageView
            dot.setImageDrawable(if (i == selectedPosition) dotOnDrawable else dotOffDrawable)
        }
    }
}
