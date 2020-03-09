package com.nankung.kotlinmvvmstructure.view.module.main

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nankung.kotlinmvvmstructure.R
import com.nankung.kotlinmvvmstructure.view.module.main.adapter.RecyclerViewMovieAdapter
import com.nankung.kotlinmvvmstructure.view.util.obtainViewModel
import com.nankung.network.remote.Status
import com.nankung.common.module.base.URLService
import com.nankung.common.module.base.mvvm.activity.AppMvvmActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppMvvmActivity() {

    lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = obtainViewModel()
        viewModel.initPopularData(URLService.tmdbApiKey)
        initialObServe()


    }

    private fun initialObServe(){
        viewModel.requestPopularResource.observe(this, Observer {
            when(it.status){
                Status.SUCCESS ->{
                    it.data.let { data->
                        recyclerviewMovie.apply {
                            val movieAdapter = RecyclerViewMovieAdapter(this@MainActivity,data!!)
                            layoutManager = LinearLayoutManager(this@MainActivity)
                            adapter = movieAdapter
                            movieAdapter.notifyDataSetChanged()
                            Log.d("Room", "> $it")
                        }
                    }
                    Log.d("CallStatus","> is  SUCCESS ${it.message}")
                }
                Status.LOADING ->{
                    Log.d("CallStatus","> is LOADING ${it.message}")
                }
                Status.EMPTY ->{
                    Log.d("CallStatus","> is EMPTY ${it.message}")
                }
                Status.ERROR -> {
                    Log.d("CallStatus","> is ERROR ${it.message}")
                    //Do something as Show Dialog Timeout or Handler something
                }
            }

        })
    }
    private fun obtainViewModel(): MainViewModel = obtainViewModel(MainViewModel::class.java)
}
