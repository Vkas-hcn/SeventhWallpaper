package com.serven.pet.seventhwallpaper.view

import android.view.KeyEvent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.serven.pet.seventhwallpaper.R
import com.serven.pet.seventhwallpaper.base.BaseActivity
import com.serven.pet.seventhwallpaper.databinding.ActivityMainBinding
import com.serven.pet.seventhwallpaper.databinding.ActivityStartBinding
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.withTimeout

class StartActivity : BaseActivity<ActivityStartBinding>() {
    val jumpToMain = MutableLiveData<Boolean>()
    override fun getLayoutResId(): Int {
        return R.layout.activity_start
    }

    override fun setupViews() {
        jumpToMain.observe(this) {
            if (it) {
                jumpToMain()
            }
        }
    }

    override fun initData() {
        lifecycleScope.launchWhenCreated {
            binding.progressBar.progress = 0
            try {
                withTimeout(2000) {
                    while (binding.progressBar.progress < 100 && isActive) {
                        delay(20)
                        binding.progressBar.progress = binding.progressBar.progress + 1
                    }
                }
            } catch (e: TimeoutCancellationException) {
                binding.progressBar.progress = 100
                jumpToMain.value = true
            }
        }
    }

    private fun jumpToMain() {
        startActivityBase<MainActivity>()
        finish()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return keyCode == KeyEvent.KEYCODE_BACK
    }
}