package com.serven.pet.seventhwallpaper.view

import android.app.WallpaperManager
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.serven.pet.seventhwallpaper.R
import com.serven.pet.seventhwallpaper.base.BaseActivity
import com.serven.pet.seventhwallpaper.databinding.ActivityDetailBinding
import com.serven.pet.seventhwallpaper.databinding.ActivityMainBinding
import com.serven.pet.seventhwallpaper.set.CircleIndicator
import com.serven.pet.seventhwallpaper.set.SevenSetUtils
import android.Manifest
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class DetailActivity : BaseActivity<ActivityDetailBinding>() {

    override fun getLayoutResId(): Int {
        return R.layout.activity_detail
    }

    var imgName = ""
    override fun setupViews() {
        binding.tvApply.setOnClickListener {
            binding.clDialog.visibility = android.view.View.VISIBLE
            binding.tvCancel.visibility = android.view.View.VISIBLE
            binding.tvApply.visibility = android.view.View.GONE
            binding.tvDownload.visibility = android.view.View.GONE
        }
        binding.tvCancel.setOnClickListener {
            binding.clDialog.visibility = android.view.View.GONE
            binding.tvCancel.visibility = android.view.View.GONE
            binding.tvApply.visibility = android.view.View.VISIBLE
            binding.tvDownload.visibility = android.view.View.VISIBLE
        }
        binding.tvHome.setOnClickListener {
            SevenSetUtils.setWallpaperFun(
                this,
                SevenSetUtils.getImgByName(imgName),
                WallpaperManager.FLAG_SYSTEM,
                startSet = {
                    binding.pbSet.visibility = android.view.View.VISIBLE
                },
                setCompat = {
                    binding.pbSet.visibility = android.view.View.GONE
                    binding.clDialog.visibility = android.view.View.GONE
                    binding.tvCancel.visibility = android.view.View.GONE
                    binding.tvApply.visibility = android.view.View.VISIBLE
                    binding.tvDownload.visibility = android.view.View.VISIBLE
                })
        }
        binding.tvLock.setOnClickListener {
            SevenSetUtils.setWallpaperFun(
                this,
                SevenSetUtils.getImgByName(imgName),
                WallpaperManager.FLAG_LOCK,
                startSet = {
                    binding.pbSet.visibility = android.view.View.VISIBLE
                },
                setCompat = {
                    binding.pbSet.visibility = android.view.View.GONE
                    binding.clDialog.visibility = android.view.View.GONE
                    binding.tvCancel.visibility = android.view.View.GONE
                    binding.tvApply.visibility = android.view.View.VISIBLE
                    binding.tvDownload.visibility = android.view.View.VISIBLE
                })
        }
        binding.tvBoth.setOnClickListener {
            SevenSetUtils.setWallpaperBoth(
                this,
                SevenSetUtils.getImgByName(imgName),
                startSet = {
                    binding.pbSet.visibility = android.view.View.VISIBLE
                },
                setCompat = {
                    binding.pbSet.visibility = android.view.View.GONE
                    binding.clDialog.visibility = android.view.View.GONE
                    binding.tvCancel.visibility = android.view.View.GONE
                    binding.tvApply.visibility = android.view.View.VISIBLE
                    binding.tvDownload.visibility = android.view.View.VISIBLE
                })
        }
        binding.tvDownload.setOnClickListener {
            downImageInt()
        }
    }

    override fun initData() {
        imgName = intent.getStringExtra("imgName") ?: ""
        binding.imgDetail.setImageResource(SevenSetUtils.getImgByName(imgName))
    }

    private fun downImageInt(){
        if (!checkPermission()) {
            requestPermission()
            return
        }
        lifecycleScope.launch {
            SevenSetUtils.saveImageToGallery(this@DetailActivity, imgName)
        }

    }

    private fun checkPermission(): Boolean {
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            true
        }
    }

    private fun requestPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                0x8545
            )
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            0x8545 -> {
                downImageInt()
            }
        }
    }
}