package com.nankung.common.module.extension.util

import android.content.Context
import android.content.pm.PackageManager

object AndroidUtility {
    fun isAppInstalled(context: Context, packageName: String): Boolean {
        try {
            context.packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
            return true
        } catch (ignored: PackageManager.NameNotFoundException) {
        }
        return false
    }

}