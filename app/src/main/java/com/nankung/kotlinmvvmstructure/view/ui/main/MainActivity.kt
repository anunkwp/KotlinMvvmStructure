package com.nankung.kotlinmvvmstructure.view.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nankung.common.module.dialog.showChoiceDialog
import com.nankung.kotlinmvvmstructure.R
import com.nankung.kotlinmvvmstructure.view.ui.main.adapter.RecyclerViewMovieAdapter
import com.nankung.kotlinmvvmstructure.view.util.obtainViewModel
import com.nankung.network.remote.Status
import com.nankung.common.module.base.URLService
import com.nankung.common.module.base.interfaces.RecyclerViewInterfaceListener
import com.nankung.common.module.base.mvvm.activity.AppMvvmActivity
import com.nankung.network.model.exeption.ErrorConverter
import jp.wasabeef.recyclerview.adapters.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppMvvmActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = obtainViewModel()
        viewModel.initPopularData(URLService.tmdbApiKey)
        initialObServe()
    }

    @SuppressLint("LogNotTimber")
    private fun initialObServe() {
        viewModel.requestPopularResource.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data.let { data ->
                        recyclerviewMovie.apply {
                            val movieAdapter = RecyclerViewMovieAdapter(this@MainActivity, data!!)
                            layoutManager = LinearLayoutManager(this@MainActivity)
                            adapter = SlideInRightAnimationAdapter(movieAdapter).apply {
                                setDuration(500)
                                setInterpolator(OvershootInterpolator())
                                setFirstOnly(false)
                            }
                            addOnItemTouchListener(RecyclerViewInterfaceListener(this,
                                object : RecyclerViewInterfaceListener.ClickListener {
                                    override fun onClick(view: View, position: Int) {
                                        Toast.makeText(context!!,"${data[position].popularity}",Toast.LENGTH_SHORT).show()
                                    }
                                }))
                            movieAdapter.notifyDataSetChanged()
                            scheduleLayoutAnimation()
                            Log.d("Room ", "$it")
                        }
                    }
                    Log.d("is SUCCESS", " ${it.message}")
                }
                Status.LOADING -> {
                    Log.d("is LOADING", " ${it.message}")
                }
                Status.EMPTY -> {
                    Log.d("is EMPTY", " ${it.message}")
                }
                Status.ERROR -> {
                    it.message.let { error ->
                        if (!error.isNullOrEmpty()){
                            val messageError = ErrorConverter.handlerErrorConverter(error)
                            checkHandlerConnectionMessage(messageError)
                        }
                    }
                }
            }
        })
    }

    private fun obtainViewModel(): MainViewModel = obtainViewModel(MainViewModel::class.java)
}
