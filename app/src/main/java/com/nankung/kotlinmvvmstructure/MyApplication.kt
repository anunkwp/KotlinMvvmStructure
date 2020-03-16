package com.nankung.kotlinmvvmstructure

import timber.log.Timber
import timber.log.Timber.DebugTree
import java.util.*


/**
 * Created by 「 Nan Kung 」 on 16/3/2563. ^^
 */
class MyApplication : LocalizeApplication() {

    override fun onCreate() {
        super.onCreate()
        //initialTimber for Debug
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }

    override fun getDefaultLanguage(): Locale = Locale.getDefault()
}