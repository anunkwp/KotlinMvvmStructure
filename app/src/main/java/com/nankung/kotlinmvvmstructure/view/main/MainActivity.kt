package com.nankung.kotlinmvvmstructure.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nankung.kotlinmvvmstructure.R
import com.nankung.kotlinmvvmstructure.view.main.adapter.RecyclerViewMovieAdapter
import com.nankung.kotlinmvvmstructure.view.util.obtainViewModel
import com.nankung.network.remote.ApiResponse
import com.nankung.network.remote.Resource
import com.nankung.network.remote.Status
import com.nankung.network.service.URLService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = obtainViewModel()
        viewModel.initData(URLService.tmdbApiKey)
        initialObServe()

    }

    private fun initialObServe(){
        viewModel.requestPopularResource.observe(this, Observer {
            when(it.status){
                Status.SUCCESS ->{
                    recyclerviewMovie.apply {
                        val movieAdapter = RecyclerViewMovieAdapter(this@MainActivity, it.data!!)
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = movieAdapter
                        movieAdapter.notifyDataSetChanged()
                        Log.d("Room", "> ${it.data}")
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
