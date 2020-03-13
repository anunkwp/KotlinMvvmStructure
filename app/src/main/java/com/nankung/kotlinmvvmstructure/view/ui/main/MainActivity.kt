package com.nankung.kotlinmvvmstructure.view.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nankung.kotlinmvvmstructure.R
import com.nankung.kotlinmvvmstructure.view.ui.main.adapter.RecyclerViewMovieAdapter
import com.nankung.kotlinmvvmstructure.view.util.obtainViewModel
import com.nankung.common.module.base.URLService
import com.nankung.common.module.base.interfaces.RecyclerViewInterfaceListener
import com.nankung.common.module.base.mvvm.activity.AppMvvmActivity
import com.nankung.network.model.exeption.ErrorConverter
import com.nankung.network.model.response.result.MoviesResult
import com.nankung.network.remote.Status.*
import jp.wasabeef.recyclerview.adapters.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppMvvmActivity() {

    lateinit var viewModel: MainViewModel
    var movieAdapter: RecyclerViewMovieAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = obtainViewModel()
        viewModel.initPopularData(URLService.tmdbApiKey)
        initialObServe()
        btnNowPlay.setOnClickListener(onButtonClick())
        btnPopular.setOnClickListener(onButtonClick())
        btnTopRated.setOnClickListener(onButtonClick())
        btnUpcoming.setOnClickListener(onButtonClick())


    }

    @SuppressLint("LogNotTimber")
    private fun onButtonClick(): View.OnClickListener? {
        return View.OnClickListener {
            when (it) {
                btnNowPlay -> {
                    viewModel.requestNowPlayingResource().observe(this@MainActivity, Observer {
                        when (it.status) {
                            SUCCESS -> {
                                it.data.let { data ->
                                    initRecyclerView(data)
                                }
                            }
                            LOADING -> {
                                Log.d("is LOADING", " ${it.message}")
                            }
                            ERROR -> {
                                it.message.let { error ->
                                    val messageError = ErrorConverter.handlerErrorConverter(error!!)
                                    checkHandlerConnectionMessage(messageError)
                                }
                            }
                        }
                    })

                }
                btnPopular -> {
                    viewModel.requestPopularResource().observe(this@MainActivity, Observer {
                        when (it.status) {
                            SUCCESS -> {
                                it.data.let { data ->
                                    initRecyclerView(data)
                                }

                            }
                            LOADING -> {
                                Log.d("is LOADING", " ${it.message}")
                            }
                            ERROR -> {
                                it.message.let { error ->
                                    val messageError = ErrorConverter.handlerErrorConverter(error!!)
                                    checkHandlerConnectionMessage(messageError)
                                }
                            }
                        }
                    })

                }
                btnTopRated -> {
                    viewModel.requestTopRatedResource().observe(this@MainActivity, Observer {
                        when (it.status) {
                            SUCCESS -> {
                                it.data.let { data ->
                                    initRecyclerView(data)
                                }
                            }
                            LOADING -> {
                                Log.d("is LOADING", " ${it.message}")
                            }
                            ERROR -> {
                                it.message.let { error ->
                                    val messageError = ErrorConverter.handlerErrorConverter(error!!)
                                    checkHandlerConnectionMessage(messageError)
                                }
                            }
                        }
                    })
                }
                btnUpcoming -> {
                    viewModel.requestUpcomingResource().observe(this@MainActivity, Observer {
                        when (it.status) {
                            SUCCESS -> {
                                it.data.let { data ->
                                    initRecyclerView(data)
                                }
                            }
                            LOADING -> {
                                Log.d("is LOADING", " ${it.message}")
                            }
                            ERROR -> {
                                it.message.let { error ->
                                    val messageError = ErrorConverter.handlerErrorConverter(error!!)
                                    checkHandlerConnectionMessage(messageError)
                                }
                            }
                        }
                    })

                }

            }
        }
    }

    @SuppressLint("LogNotTimber")
    private fun initRecyclerView(data: List<MoviesResult>?) {
        recyclerviewMovie.apply {
            movieAdapter = RecyclerViewMovieAdapter(this@MainActivity, data!!)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = SlideInRightAnimationAdapter(movieAdapter).apply {
                setDuration(500)
                setInterpolator(OvershootInterpolator())
                setFirstOnly(false)
            }
            movieAdapter!!.notifyDataSetChanged()
            addOnItemTouchListener(
                RecyclerViewInterfaceListener(this,
                    object : RecyclerViewInterfaceListener.ClickListener {
                        override fun onClick(view: View, position: Int) {
                            Toast.makeText(
                                context!!,
                                "${data[position].title}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
            )
            scheduleLayoutAnimation()
            Log.d("RoomNow", " ${data.toString()}")
        }
    }

    @SuppressLint("LogNotTimber")
    private fun initialObServe() {
        viewModel.requestTopRatedResource().observe(this@MainActivity, Observer {
            when (it.status) {
                SUCCESS -> {
                    it.data.let { data ->
                        initRecyclerView(data)
                    }
                }
                LOADING -> {
                    Log.d("is LOADING", " ${it.message}")
                }
                ERROR -> {
                    it.message.let { error ->
                        val messageError = ErrorConverter.handlerErrorConverter(error!!)
                        checkHandlerConnectionMessage(messageError)
                    }
                }
            }
        })

    }

private fun obtainViewModel(): MainViewModel = obtainViewModel(MainViewModel::class.java)
}
