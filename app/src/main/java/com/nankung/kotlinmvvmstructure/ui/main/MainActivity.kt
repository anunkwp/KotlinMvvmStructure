package com.nankung.kotlinmvvmstructure.ui.main

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.nankung.kotlinmvvmstructure.R
import com.nankung.common.module.base.mvvm.activity.AppMvvmActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.ui.AppBarConfiguration
import com.nankung.kotlinmvvmstructure.events.GoToDashBoard
import com.nankung.kotlinmvvmstructure.ui.dashboard.DashboardFragment
import com.nankung.kotlinmvvmstructure.util.obtainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppMvvmActivity() {

    private lateinit var viewModel: MainViewModel
    private var navController:NavController?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

         navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
        setupActionBarWithNavController(navController!!, appBarConfiguration)
        navView.setupWithNavController(navController!!)
        viewModel = obtainViewModel()


    }

    override fun onStart() {
        super.onStart()
        //ต้องเอามาไว้ใน onStart เท่านั้น ไม่รู้เป็นเหี้ยไร
        viewModel.subscribeBus(this@MainActivity, observer)
    }

    private fun obtainViewModel(): MainViewModel = obtainViewModel(MainViewModel::class.java)

    private var observer: Observer<Any> = Observer { event ->
        when (event) {
            is GoToDashBoard -> {
                navController?.navigate(R.id.action_navigation_home_to_navigation_dashboard)
                }
            }
        }
    }





