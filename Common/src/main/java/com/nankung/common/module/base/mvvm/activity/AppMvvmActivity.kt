package com.nankung.common.module.base.mvvm.activity

import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Toast
import com.nankung.common.module.base.mvvm.localize.LocalizationHelperActivity
import com.nankung.common.module.dialog.hideLoading
import com.nankung.common.module.dialog.showChoiceDialog
import com.nankung.common.module.extension.isInternetConnected
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 * Created by「 Nan Kung 」on 09 MARCH 2020 ^^
 */

abstract class AppMvvmActivity
    : LocalizationHelperActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

}

