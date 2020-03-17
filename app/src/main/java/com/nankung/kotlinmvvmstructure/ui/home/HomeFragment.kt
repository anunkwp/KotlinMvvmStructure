package com.nankung.kotlinmvvmstructure.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nankung.common.module.base.URLService
import com.nankung.common.module.base.interfaces.BaseRecyclerViewItemTouchListener
import com.nankung.common.module.base.mvvm.fragment.AppMvvmFragment
import com.nankung.common.module.dialog.hideLoading
import com.nankung.common.module.dialog.showGradientLoading

import com.nankung.kotlinmvvmstructure.R
import com.nankung.kotlinmvvmstructure.events.GoToDashBoard
import com.nankung.kotlinmvvmstructure.ui.main.adapter.RecyclerViewMovieAdapter
import com.nankung.kotlinmvvmstructure.util.ResponseConverter
import com.nankung.kotlinmvvmstructure.util.obtainMovieViewModel
import com.nankung.network.model.exeption.ErrorConverter
import com.nankung.network.model.response.result.MoviesResult
import com.nankung.network.remote.Status
import jp.wasabeef.recyclerview.adapters.SlideInRightAnimationAdapter
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : AppMvvmFragment() {

    companion object {
        //สร้าง Instance ไว้เพื่อเปิด Fragment แล้วจะโยนอะไรมาก็เอามาเก็บไว้ใน Bundle
        fun newInstance(): HomeFragment {
            val fragment = HomeFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var viewModel: HomeViewModel
    private var movieAdapter: RecyclerViewMovieAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    private fun obtainViewModel(): HomeViewModel = obtainMovieViewModel(
        HomeViewModel::class.java)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = obtainViewModel()
        viewModel.initPopularData(URLService.tmdbApiKey)
        initialObServe()
        initListener()
    }

    private fun initListener() {
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
                    viewModel.requestNowPlayingResource()
                        .observe(viewLifecycleOwner, Observer { response ->
                            when (response.status) {
                                Status.SUCCESS -> {
                                    hideLoading()
                                    initRecyclerView(response.data)
                                }
                                Status.LOADING -> {
                                    showGradientLoading()
                                }
                                Status.ERROR -> {
                                    response.message.let { error ->
                                        val messageError =
                                            ErrorConverter.handlerErrorConverter(error!!)
                                        checkHandlerConnectionMessage(messageError)
                                    }
                                }
                            }
                        })

                }
                btnPopular -> {
                    viewModel.requestPopularResource()
                        .observe(viewLifecycleOwner, Observer { response ->
                            when (response.status) {
                                Status.SUCCESS -> {
                                    hideLoading()
                                    initRecyclerView(response.data)
                                }
                                Status.LOADING -> {
                                    showGradientLoading()
                                }
                                Status.ERROR -> {
                                    response.message.let { error ->
                                        val messageError =
                                            ErrorConverter.handlerErrorConverter(error!!)
                                        checkHandlerConnectionMessage(messageError)
                                    }
                                }
                            }
                        })

                }
                btnTopRated -> {
                    viewModel.requestTopRatedResource()
                        .observe(viewLifecycleOwner, Observer { response ->
                            when (response.status) {
                                Status.SUCCESS -> {
                                    hideLoading()
                                    initRecyclerView(response.data)
                                }
                                Status.LOADING -> {
                                    showGradientLoading()
                                }
                                Status.ERROR -> {
                                    response.message.let { error ->
                                        val messageError =
                                            ErrorConverter.handlerErrorConverter(error!!)
                                        checkHandlerConnectionMessage(messageError)
                                    }
                                }
                            }
                        })
                }
                btnUpcoming -> {
                    viewModel.postEvent(GoToDashBoard())
                    viewModel.requestUpcomingResource()
                        .observe(viewLifecycleOwner, Observer { response ->
                            when (response.status) {
                                Status.SUCCESS -> {
                                    hideLoading()
                                    initRecyclerView(response.data)

                                }
                                Status.LOADING -> {
                                    showGradientLoading()
                                }
                                Status.ERROR -> {
                                    response.message.let { error ->
                                        val messageError =
                                            ErrorConverter.handlerErrorConverter(error!!)
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
            movieAdapter = RecyclerViewMovieAdapter(context, data!!)
            layoutManager = LinearLayoutManager(context)
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
            //Log.d("RoomNow", " $data")
        }
    }
    @SuppressLint("LogNotTimber")
    private fun initialObServe() {
        viewModel.requestTopRatedResource().observe(viewLifecycleOwner, Observer { response ->
            when (response.status) {
                Status.SUCCESS -> {
                    hideLoading()
                    val data =  ResponseConverter.convertTopRateDESC(response)
                    initRecyclerView(data)

                }
                Status.LOADING -> {
                    showGradientLoading()
                }
                Status.ERROR -> {
                    response.message.let { error ->
                        val messageError = ErrorConverter.handlerErrorConverter(error!!)
                        checkHandlerConnectionMessage(messageError)
                    }
                }
            }
        })

    }


}
