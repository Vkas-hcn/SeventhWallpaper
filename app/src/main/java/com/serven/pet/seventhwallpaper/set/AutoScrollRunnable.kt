package com.serven.pet.seventhwallpaper.set

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.serven.pet.seventhwallpaper.set.SevenSetUtils.AUTO_SCROLL_INTERVAL

class AutoScrollRunnable(private val recyclerView: RecyclerView) : Runnable {
    override fun run() {
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        recyclerView.smoothScrollToPosition((layoutManager.findFirstVisibleItemPosition() + 1) % recyclerView.adapter!!.itemCount)

        recyclerView.postDelayed(this, AUTO_SCROLL_INTERVAL)
    }
}
