package com.serven.pet.seventhwallpaper.set

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import android.telephony.TelephonyManager
import com.serven.pet.seventhwallpaper.App
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

object ClockUtils {
    private val sharedPreferences by lazy {
        App.appContext.getSharedPreferences(
            "Animal",
            Context.MODE_PRIVATE
        )
    }
    var uu_animal = ""
        set(value) {
            sharedPreferences.edit().run {
                putString("uu_animal", value)
                commit()
            }
            field = value
        }
        get() = sharedPreferences.getString("uu_animal", "").toString()
    var animal_clock = ""
        set(value) {
            sharedPreferences.edit().run {
                putString("animal_clock", value)
                commit()
            }
            field = value
        }
        get() = sharedPreferences.getString("animal_clock", "").toString()
    fun getHmdData(context: Context): Map<String, Any> {
        return mapOf<String, Any>(
            //distinct_id
            "thespian" to (uu_animal),
            //client_ts
            "hadley" to (System.currentTimeMillis()),
            //device_model
            "atkinson" to Build.MODEL,
            //bundle_id
            "indigo" to ("com.wallpapercollection.animals"),
            //os_version
            "fry" to Build.VERSION.RELEASE,
            //gaid
            "checksum" to "",
            //android_id
            "singsong" to Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID),
            //os
            "acme" to "ethyl",
            //app_version
            "keaton" to getAppVersion(context),//应用的版本
            //brand
            "grey" to ((context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager).networkOperator)
        )
    }
    private fun getAppVersion(context: Context): String {
        try {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)

            return packageInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return "Version information not available"
    }


    fun getCloadNet(url: String, map: Map<String, Any>, onSucess: (response: String) -> Unit, onError: (error: String) -> Unit) {
        val urlBuilder = StringBuilder(url)

        if (map.isNotEmpty()) {
            urlBuilder.append("?")
            map.forEach { (key, value) ->
                urlBuilder.append(URLEncoder.encode(key, StandardCharsets.UTF_8.toString()))
                    .append("=")
                    .append(URLEncoder.encode(value.toString(), StandardCharsets.UTF_8.toString()))
                    .append("&")
            }
            // 移除末尾的 "&" 符号
            urlBuilder.setLength(urlBuilder.length - 1)
        }

        val urlString = urlBuilder.toString()

        val url = URL(urlString)
        val urlConnection = url.openConnection() as HttpURLConnection

        try {
            val inputStream = urlConnection.inputStream
            val reader = BufferedReader(InputStreamReader(inputStream))
            val response = StringBuilder()
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                response.append(line)
            }

            onSucess(response.toString())
        } catch (e: IOException) {
            onError("Network error")
        } finally {
            urlConnection.disconnect()
        }
    }

}