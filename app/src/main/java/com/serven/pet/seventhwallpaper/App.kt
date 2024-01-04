package com.serven.pet.seventhwallpaper

import android.app.Application
import android.content.Context
import android.util.Log
import com.serven.pet.seventhwallpaper.set.ClockUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.UUID

class App : Application() {
    companion object {
        lateinit var appContext: Application
    }

    override fun onCreate() {
        appContext = this
        super.onCreate()
        val data = ClockUtils.uu_animal
        if (data.isEmpty()) {
            ClockUtils.uu_animal = UUID.randomUUID().toString()
        }
        getBlackList(this)
    }

    private fun getBlackList(context: Context) {
        if (ClockUtils.animal_clock.isNotEmpty()) {
            return
        }
        GlobalScope.launch(Dispatchers.IO) {
            val map = ClockUtils.getHmdData(context)
            ClockUtils.getCloadNet(
                "https://morose.wallpapercollectionanimals.com/brass/wordy/glare",
                map,
                onSucess = {
                    Log.e("TAG", "The blacklist request is successful：$it")
                    ClockUtils.animal_clock = it
                },
                onError = {
                    retry(it, context)
                })
        }
    }

    //请求重试
    private fun retry(it: String, context: Context) {
        GlobalScope.launch(Dispatchers.IO) {
            delay(10000)
            Log.e("TAG", "The blacklist request failed：$it")
            getBlackList(context)
        }
    }
}