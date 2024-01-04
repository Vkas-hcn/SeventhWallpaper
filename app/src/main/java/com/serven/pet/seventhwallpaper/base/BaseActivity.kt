package com.serven.pet.seventhwallpaper.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {

    lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutResId())
        setupViews()
        initData()
    }

    abstract fun getLayoutResId(): Int

    abstract fun setupViews()

    abstract fun initData()

    // 跳转到目标 Activity
    inline fun <reified T : AppCompatActivity> startActivityBase() {
        val intent = Intent(this, T::class.java)
        startActivity(intent)
    }

    // 带参数跳转到目标 Activity
    inline fun <reified T : AppCompatActivity> startActivityWithParams(params: Bundle) {
        val intent = Intent(this, T::class.java)
        intent.putExtras(params)
        startActivity(intent)
    }

    companion object {
        // 启动当前 Activity 的静态方法
        inline fun <reified T : AppCompatActivity> startActivity(context: Context) {
            val intent = Intent(context, T::class.java)
            context.startActivity(intent)
        }

        // 带参数启动当前 Activity 的静态方法
        inline fun <reified T : AppCompatActivity> startActivityWithParams(
            context: Context,
            params: Bundle
        ) {
            val intent = Intent(context, T::class.java)
            intent.putExtras(params)
            context.startActivity(intent)
        }
    }
}
