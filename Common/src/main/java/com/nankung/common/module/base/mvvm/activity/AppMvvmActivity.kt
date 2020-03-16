package com.nankung.common.module.base.mvvm.activity

import android.app.ActivityManager
import android.content.ComponentName
import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import com.nankung.common.module.base.mvvm.localize.LocalizationHelperActivity
import com.nankung.common.module.dialog.hideLoading
import com.nankung.common.module.dialog.showChoiceDialog
import com.nankung.common.module.extension.isInternetConnected
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber


/**
 * Created by「 Nan Kung 」on 09 MARCH 2020 ^^
 */

abstract class AppMvvmActivity
    : LocalizationHelperActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("${this.javaClass.simpleName} of onCreate called.")
        
    }

    override fun onStart() {
        super.onStart()
        Timber.d("${this.javaClass.simpleName} of onStart called.")
    }

    override fun onResume() {
        super.onResume()
        Timber.d("${this.javaClass.simpleName} of onResume called.")
    }

    override fun onPause() {
        super.onPause()
        Timber.d("${this.javaClass.simpleName} of onPause called.")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("${this.javaClass.simpleName} of onDestroy called.")
    }

    fun checkHandlerConnectionMessage(messageError:List<String>?){
        if (isInternetConnected){
            showChoiceDialog(title = messageError!![0], message = messageError[1])
        }
        else{
            showChoiceDialog(title = "No Internet!!",message = "Please check your internet connection.")
            hideLoading()
        }
    }

    fun requestLogout(){
        //เอา่ไว้ Handler ถ้าเกิดแอพ session หมดอายุ หรือ token ตาย ก็ให้มันไป Login หรืออะไรก็แล้วแต่
    }
}

