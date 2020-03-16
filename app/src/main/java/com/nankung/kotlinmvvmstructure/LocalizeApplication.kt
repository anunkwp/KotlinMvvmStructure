package com.nankung.kotlinmvvmstructure

import android.content.Context
import android.content.res.Configuration
import androidx.multidex.MultiDexApplication
import com.akexorcist.localizationactivity.core.LocalizationApplicationDelegate
import java.util.*

/**
 * Created by 「 Nan Kung 」 on 16/3/2563. ^^
 */

abstract class LocalizeApplication : MultiDexApplication(){

    private var localizationDelegate = LocalizationApplicationDelegate()

    override fun attachBaseContext(base: Context) {
        localizationDelegate.setDefaultLanguage(base, getDefaultLanguage())
        super.attachBaseContext(localizationDelegate.attachBaseContext(base))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        localizationDelegate.onConfigurationChanged(this)
    }

    override fun getApplicationContext(): Context {
        return localizationDelegate.getApplicationContext(super.getApplicationContext())
    }

    abstract fun getDefaultLanguage(): Locale
}