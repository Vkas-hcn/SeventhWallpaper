package com.serven.pet.seventhwallpaper.view

import android.util.Log
import androidx.activity.addCallback
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.serven.pet.seventhwallpaper.R
import com.serven.pet.seventhwallpaper.base.BaseActivity
import com.serven.pet.seventhwallpaper.databinding.ActivityMainBinding
import com.serven.pet.seventhwallpaper.set.AutoScrollRunnable
import com.serven.pet.seventhwallpaper.set.CircleIndicator
import com.serven.pet.seventhwallpaper.set.SevenSetUtils.AUTO_SCROLL_INTERVAL
import com.serven.pet.seventhwallpaper.set.SevenSetUtils.allData
import com.serven.pet.seventhwallpaper.set.SevenSetUtils.bannerData

class MainActivity  : BaseActivity<ActivityMainBinding>() {

    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }
    private lateinit var indicator: CircleIndicator

    override fun setupViews() {
        binding.icPaw.setOnClickListener {
            binding.drawerLayoutMain.openDrawer(GravityCompat.END)
        }
        binding.llPp.setOnClickListener {
            binding.drawerLayoutMain.closeDrawer(GravityCompat.END)
            startActivityBase<AgreeActivity>()
        }
        onBackPressedDispatcher.addCallback(this) {
            if (binding.drawerLayoutMain.isDrawerOpen(GravityCompat.END)) {
                binding.drawerLayoutMain.closeDrawer(GravityCompat.END)
            } else {
                finish()
            }
        }
    }

    override fun initData() {
        initBanner()
        initAll()
    }

    private fun initBanner(){
        val bannerAdapter = BannerAdapter(bannerData)
        binding.recyclerView.adapter = bannerAdapter
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerView.layoutManager = layoutManager
        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(binding.recyclerView)
        indicator = CircleIndicator(this, binding.indicatorLayout, binding.recyclerView)
        indicator.createDotPanel(bannerData.size)
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val position = layoutManager.findFirstCompletelyVisibleItemPosition()
                indicator.updateIndicator(position % bannerData.size)
            }
        })
        bannerAdapter.setOnItemClickListener(object : BannerAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                jumpToDetail(bannerData[position])
            }
        })

        val autoScrollRunnable = AutoScrollRunnable( binding.recyclerView)
        binding.recyclerView.postDelayed(autoScrollRunnable, AUTO_SCROLL_INTERVAL)
    }

    private fun initAll(){
        val allAdapter = AllAdapter(allData)
        binding.rvAll.adapter = allAdapter
        val gridLayoutManager = GridLayoutManager(this, 3)
        binding.rvAll.layoutManager = gridLayoutManager
        allAdapter.setOnItemClickListener(object : AllAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                jumpToDetail(allData[position])
            }
        })
    }


    //跳转到详情页
    private fun jumpToDetail(imgName: String) {
        val params = android.os.Bundle()
        params.putString("imgName", imgName)
        startActivityWithParams<DetailActivity>(params)
    }
}