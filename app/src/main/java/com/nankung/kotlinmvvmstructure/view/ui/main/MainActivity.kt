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
import com.nankung.common.module.base.interfaces.BaseRecyclerViewItemTouchListener
import com.nankung.common.module.base.mvvm.activity.AppMvvmActivity
import com.nankung.network.model.exeption.ErrorConverter
import com.nankung.network.model.response.result.MoviesResult
import com.nankung.network.remote.Status.*
import jp.wasabeef.recyclerview.adapters.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppMvvmActivity() {

    private  lateinit var viewModel: MainViewModel
    private var movieAdapter: RecyclerViewMovieAdapter? = null
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
                    viewModel.requestNowPlayingResource().observe(this@MainActivity, Observer { response ->
                        when (response.status) {
                            SUCCESS -> {
                                initRecyclerView(response.data)
                            }
                            LOADING -> {
                                Log.d("is LOADING", " ${response.message}")
                            }
                            ERROR -> {
                                response.message.let { error ->
                                    val messageError = ErrorConverter.handlerErrorConverter(error!!)
                                    checkHandlerConnectionMessage(messageError)
                                }
                            }
                        }
                    })

                }
                btnPopular -> {
                    viewModel.requestPopularResource().observe(this@MainActivity, Observer {response ->
                        when (response.status) {
                            SUCCESS -> {
                                initRecyclerView(response.data)

                            }
                            LOADING -> {
                                Log.d("is LOADING", " ${response.message}")
                            }
                            ERROR -> {
                                response.message.let { error ->
                                    val messageError = ErrorConverter.handlerErrorConverter(error!!)
                                    checkHandlerConnectionMessage(messageError)
                                }
                            }
                        }
                    })

                }
                btnTopRated -> {
                    viewModel.requestTopRatedResource().observe(this@MainActivity, Observer {response ->
                        when (response.status) {
                            SUCCESS -> {
                                initRecyclerView(response.data)
                            }
                            LOADING -> {
                                Log.d("is LOADING", " ${response.message}")
                            }
                            ERROR -> {
                                response.message.let { error ->
                                    val messageError = ErrorConverter.handlerErrorConverter(error!!)
                                    checkHandlerConnectionMessage(messageError)
                                }
                            }
                        }
                    })
                }
                btnUpcoming -> {
                    viewModel.requestUpcomingResource().observe(this@MainActivity, Observer {response ->
                        when (response.status) {
                            SUCCESS -> {
                                initRecyclerView(response.data)
                            }
                            LOADING -> {
                                Log.d("is LOADING", " ${response.message}")
                            }
                            ERROR -> {
                                response.message.let { error ->
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
                BaseRecyclerViewItemTouchListener(this,
                    object : BaseRecyclerViewItemTouchListener.ClickListener {
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
            Log.d("RoomNow", " $data")
        }
    }

    @SuppressLint("LogNotTimber")
    private fun initialObServe() {
        viewModel.requestTopRatedResource().observe(this@MainActivity, Observer {response->
            when (response.status) {
                SUCCESS -> {
                    initRecyclerView(response.data)
                }
                LOADING -> {
                    Log.d("is LOADING", " ${response.message}")
                }
                ERROR -> {
                    response.message.let { error ->
                        val messageError = ErrorConverter.handlerErrorConverter(error!!)
                        checkHandlerConnectionMessage(messageError)
                    }
                }
            }
        })

    }

private fun obtainViewModel(): MainViewModel = obtainViewModel(MainViewModel::class.java)
}
