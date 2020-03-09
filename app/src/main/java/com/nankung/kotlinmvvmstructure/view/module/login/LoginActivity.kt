package com.nankung.kotlinmvvmstructure.view.module.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import com.nankung.common.module.base.URLService
import com.nankung.common.module.base.mvvm.activity.AppMvvmActivity
import com.nankung.kotlinmvvmstructure.R
import com.nankung.kotlinmvvmstructure.view.module.main.MainActivity
import com.nankung.kotlinmvvmstructure.view.module.main.MainViewModel
import com.nankung.kotlinmvvmstructure.view.util.obtainViewModel
import com.nankung.network.model.body.ValidateBody
import com.nankung.network.remote.Status
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity :AppMvvmActivity(){
    lateinit var viewModel : MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = obtainViewModel()
        viewModel.initPopularData(URLService.tmdbApiKey)
        initialObServe()


    }

    private fun callValidateToken(){
        viewModel.requestValidateToken.observe(this, Observer {
            when(it.status){
                Status.SUCCESS ->{
                    it.data.let { data->
                      Toast.makeText(this@LoginActivity,data!!.request_token,Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@LoginActivity,MainActivity::class.java)
                        startActivity(intent)
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

    private fun initialObServe() {
        viewModel.requestNewToken.observe(this, Observer {
            when(it.status){
                Status.SUCCESS ->{
                    it.data.let { data->
                        btnLogin.setOnClickListener {
                            val username = txtUsername.text.toString().trim()
                            val password = txtPassword.text.toString().trim()
                            val body = ValidateBody(username,password,data!!.request_token)
                            viewModel.initValidate(URLService.tmdbApiKey,body)
                            callValidateToken()

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