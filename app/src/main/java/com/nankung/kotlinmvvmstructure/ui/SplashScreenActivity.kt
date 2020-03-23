package com.nankung.kotlinmvvmstructure.ui

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import com.nankung.common.module.base.mvvm.activity.AppMvvmActivity
import com.nankung.kotlinmvvmstructure.R
import com.nankung.kotlinmvvmstructure.ui.main.MainActivity
import kotlinx.coroutines.*

class SplashScreenActivity : AppMvvmActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        CoroutineScope(Dispatchers.IO).launch {
           delay(3000)
            startActivity(Intent(this@SplashScreenActivity,MainActivity::class.java))
        }
    }
}
