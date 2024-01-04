package com.serven.pet.seventhwallpaper.set

import android.Manifest
import android.app.Activity
import android.app.WallpaperManager
import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.serven.pet.seventhwallpaper.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.OutputStream

object SevenSetUtils {
    const val AUTO_SCROLL_INTERVAL = 3000L // 自动滚动间隔时间，单位：毫秒
    val bannerData = listOf(
        "ic_13",
        "ic_16",
        "ic_4",
        "ic_7",
        "ic_11",
    )
    val allData = listOf(
        "ic_1",
        "ic_2",
        "ic_3",
        "ic_4",
        "ic_5",
        "ic_6",
        "ic_7",
        "ic_8",
        "ic_9",
        "ic_10",
        "ic_11",
        "ic_12",
        "ic_13",
        "ic_14",
        "ic_15",
        "ic_16",
        "ic_17",
        "ic_18",
        "ic_19",
        "ic_20",
        "ic_21",
        "ic_22",
        "ic_23",
        "ic_24",
        "ic_25",
        "ic_26",
        "ic_27",
        "ic_28",
        "ic_29",
        "ic_30",
        "ic_31",
        "ic_32",
        "ic_33",
        "ic_34",
        "ic_35",
        "ic_36",
        "ic_37",
        "ic_38",
        "ic_39",
        "ic_40",
        "ic_41",
        "ic_42",
        "ic_43",
        "ic_44",
        "ic_45",
        "ic_46",
        "ic_47",
        "ic_48",
        "ic_49",
        "ic_50",
        "ic_51",
        "ic_52",
        "ic_53",
        "ic_54",
        "ic_55",
        "ic_56",
        "ic_57",
        "ic_58",
        "ic_59",
        "ic_60",
        "ic_61",
        "ic_62",
        "ic_63",
        "ic_64",
        "ic_65",
        "ic_66",
    )

    fun getImgByName(name: String): Int {
        return when (name) {
            "ic_1" -> R.drawable.ic_1
            "ic_2" -> R.drawable.ic_2
            "ic_3" -> R.drawable.ic_3
            "ic_4" -> R.drawable.ic_4
            "ic_5" -> R.drawable.ic_5
            "ic_6" -> R.drawable.ic_6
            "ic_7" -> R.drawable.ic_7
            "ic_8" -> R.drawable.ic_8
            "ic_9" -> R.drawable.ic_9
            "ic_10" -> R.drawable.ic_10
            "ic_11" -> R.drawable.ic_11
            "ic_12" -> R.drawable.ic_12
            "ic_13" -> R.drawable.ic_13
            "ic_14" -> R.drawable.ic_14
            "ic_15" -> R.drawable.ic_15
            "ic_16" -> R.drawable.ic_16
            "ic_17" -> R.drawable.ic_17
            "ic_18" -> R.drawable.ic_18
            "ic_19" -> R.drawable.ic_19
            "ic_20" -> R.drawable.ic_20
            "ic_21" -> R.drawable.ic_21
            "ic_22" -> R.drawable.ic_22
            "ic_23" -> R.drawable.ic_23
            "ic_24" -> R.drawable.ic_24
            "ic_25" -> R.drawable.ic_25
            "ic_26" -> R.drawable.ic_26
            "ic_27" -> R.drawable.ic_27
            "ic_28" -> R.drawable.ic_28
            "ic_29" -> R.drawable.ic_29
            "ic_30" -> R.drawable.ic_30
            "ic_31" -> R.drawable.ic_31
            "ic_32" -> R.drawable.ic_32
            "ic_33" -> R.drawable.ic_33
            "ic_34" -> R.drawable.ic_34
            "ic_35" -> R.drawable.ic_35
            "ic_36" -> R.drawable.ic_36
            "ic_38" -> R.drawable.ic_38
            "ic_39" -> R.drawable.ic_39
            "ic_40" -> R.drawable.ic_40
            "ic_41" -> R.drawable.ic_41
            "ic_42" -> R.drawable.ic_42
            "ic_43" -> R.drawable.ic_43
            "ic_44" -> R.drawable.ic_44
            "ic_45" -> R.drawable.ic_45
            "ic_46" -> R.drawable.ic_46
            "ic_47" -> R.drawable.ic_47
            "ic_48" -> R.drawable.ic_48
            "ic_49" -> R.drawable.ic_49
            "ic_50" -> R.drawable.ic_50
            "ic_51" -> R.drawable.ic_51
            "ic_52" -> R.drawable.ic_52
            "ic_53" -> R.drawable.ic_53
            "ic_54" -> R.drawable.ic_54
            "ic_55" -> R.drawable.ic_55
            "ic_56" -> R.drawable.ic_56
            "ic_57" -> R.drawable.ic_57
            "ic_58" -> R.drawable.ic_58
            "ic_59" -> R.drawable.ic_59
            "ic_60" -> R.drawable.ic_60
            "ic_61" -> R.drawable.ic_61
            "ic_62" -> R.drawable.ic_62
            "ic_63" -> R.drawable.ic_63
            "ic_64" -> R.drawable.ic_64
            "ic_65" -> R.drawable.ic_65
            "ic_66" -> R.drawable.ic_66
            else -> R.drawable.ic_1
        }
    }

    fun setWallpaperFun(
        activity: AppCompatActivity,
        drawable: Int,
        type: Int,
        startSet: () -> Unit,
        setCompat: () -> Unit
    ) {
        activity.lifecycleScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                startSet()
            }
            val bitmap: Bitmap = BitmapFactory.decodeResource(activity.resources, drawable)
            val wallpaperManager = WallpaperManager.getInstance(activity)

            try {
                wallpaperManager.setBitmap(
                    bitmap,
                    null,
                    false,
                    type
                )
                withContext(Dispatchers.Main) {
                    setCompat()
                    Toast.makeText(activity, "Set the wallpaper successfully", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(activity, "Failed to set image", Toast.LENGTH_SHORT).show()
                }
                e.printStackTrace()
            }
        }
    }

    fun setWallpaperBoth(
        activity: AppCompatActivity,
        drawable: Int,
        startSet: () -> Unit,
        setCompat: () -> Unit
    ) {
        activity.lifecycleScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                startSet()
            }
            val bitmap: Bitmap = BitmapFactory.decodeResource(activity.resources, drawable)
            val wallpaperManager = WallpaperManager.getInstance(activity)
            try {
                wallpaperManager.setBitmap(
                    bitmap,
                    null,
                    false,
                    WallpaperManager.FLAG_LOCK
                )
                wallpaperManager.setBitmap(
                    bitmap,
                    null,
                    false,
                    WallpaperManager.FLAG_SYSTEM
                )
                withContext(Dispatchers.Main) {
                    setCompat()
                    Toast.makeText(activity, "Set the wallpaper successfully", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(activity, "Failed to set image", Toast.LENGTH_SHORT).show()
                }
                e.printStackTrace()
            }
        }
    }


   suspend fun saveImageToGallery(context: Context, drawable: String) {
        val bitmap: Bitmap = BitmapFactory.decodeResource(context.resources, getImgByName(drawable))

        // Save the image to the gallery
            val savedUri = saveImage(context, bitmap)
            withContext(Dispatchers.Main) {
                if (savedUri != null) {
                    Toast.makeText(context, "Download successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Download Failed", Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun saveImage(context: Context, bitmap: Bitmap): String? {
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "Image_${System.currentTimeMillis()}")
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
            }
        }

        val resolver = context.contentResolver
        val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

        uri?.let {
            try {
                val outputStream: OutputStream? = resolver.openOutputStream(uri)
                if (outputStream != null) {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                    outputStream.close()
                }
                return uri.toString()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return null
    }

}