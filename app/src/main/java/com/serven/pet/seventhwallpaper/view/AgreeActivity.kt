package com.serven.pet.seventhwallpaper.view

import com.serven.pet.seventhwallpaper.R
import com.serven.pet.seventhwallpaper.base.BaseActivity
import com.serven.pet.seventhwallpaper.databinding.ActivityAgreeBinding

class AgreeActivity :BaseActivity<ActivityAgreeBinding>(){
    override fun getLayoutResId(): Int {
        return R.layout.activity_agree
    }

    override fun setupViews() {
        binding.webviewAgree.loadUrl("https://www.baidu.com/")
        binding.webviewAgree.webViewClient = object : android.webkit.WebViewClient() {
            override fun shouldOverrideUrlLoading(view: android.webkit.WebView?, url: String?): Boolean {
                url?.let { view?.loadUrl(it) }
                return true
            }
        }
        binding.webviewAgree
    }

    override fun initData() {
    }
}