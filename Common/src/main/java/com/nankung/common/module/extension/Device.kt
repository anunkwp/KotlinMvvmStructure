package com.nankung.common.module.extension

import android.annotation.SuppressLint
import android.content.Context
import android.net.wifi.WifiManager
import android.os.Build
import android.os.storage.StorageManager
import android.provider.Settings
import java.lang.reflect.InvocationTargetException
import java.net.NetworkInterface
import java.util.*

/**
 * Created by「 Nan Kung 」on 09 MARCH 2020 ^^
 */
val Context.deviceId: String
    @SuppressLint("HardwareIds")
    get() = this.androidID + ":" + Build.SERIAL + ":" + this.macAddress

val Context.androidID: String
    @SuppressLint("HardwareIds")
    get() = Settings.Secure.getString(this.contentResolver, Settings.Secure.ANDROID_ID)


val Context.macAddress: String
    get() {
        var macAddress = getMacAddressByWifiInfo()
        if ("02:00:00:00:00:00" != macAddress) return macAddress

        macAddress = getMacAddressByNetworkInterface()
        if ("02:00:00:00:00:00" != macAddress) return macAddress

        return "please open wifi"
    }


val Context.isSDCardEnable: Boolean get() = this.SDCardPaths.isNotEmpty()

val Context.SDCardPaths: List<String> get() {
    val storageManager = this.getSystemService(Context.STORAGE_SERVICE) as StorageManager
    var paths: List<String> = ArrayList()
    try {
        val getVolumePathsMethod = StorageManager::class.java.getMethod("getVolumePaths")
        getVolumePathsMethod.isAccessible = true
        val invoke = getVolumePathsMethod.invoke(storageManager)
        paths = Arrays.asList(invoke as String)
    } catch (e: NoSuchMethodException) {
        e.printStackTrace()
    } catch (e: IllegalAccessException) {
        e.printStackTrace()
    } catch (e: InvocationTargetException) {
        e.printStackTrace()
    }

    return paths
}

/* =========================== Private method =================================================== */

/** <p>{@code <uses-permission android:name="android.permission.INTERNET"/>}</p> **/
@SuppressLint("HardwareIds")
private fun Context.getMacAddressByWifiInfo(): String {
    return try {
        val wifi = this.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val info = wifi.connectionInfo
        info?.macAddress ?: "02:00:00:00:00:00"
    } catch (e: Exception) {
        e.printStackTrace()
        "02:00:00:00:00:00"
    }
}

private fun getMacAddressByNetworkInterface(): String {
    try {
        val nis = Collections.list<NetworkInterface>(NetworkInterface.getNetworkInterfaces())
        for (ni in nis) {
            if (ni.name.equals("wlan0", ignoreCase = true).not()) continue
            val macBytes = ni.hardwareAddress
            if (macBytes?.isNotEmpty() == true) {
                val res1 = StringBuilder()
                for (b in macBytes) {
                    res1.append(String.format("%02x:", b))
                }
                return res1.deleteCharAt(res1.length - 1).toString()
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return "02:00:00:00:00:00"
}
