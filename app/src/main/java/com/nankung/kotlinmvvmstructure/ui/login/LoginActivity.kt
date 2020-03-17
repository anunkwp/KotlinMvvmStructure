package com.nankung.kotlinmvvmstructure.ui.login

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.nankung.common.module.base.URLService
import com.nankung.common.module.base.mvvm.activity.AppMvvmActivity
import com.nankung.common.module.dialog.hideLoading
import com.nankung.common.module.dialog.showGradientLoading
import com.nankung.kotlinmvvmstructure.R
import com.nankung.kotlinmvvmstructure.ui.main.MainActivity
import com.nankung.kotlinmvvmstructure.util.obtainMovieViewModel
import com.nankung.network.model.response.body.ValidateBody
import com.nankung.network.model.exeption.ErrorConverter
import com.nankung.network.remote.Status.*
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppMvvmActivity() {
    private lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        viewModel = obtainViewModel()
        viewModel.initKeys(URLService.tmdbApiKey)
        initialObServe()
        initListener()
    }

    private fun initListener() {
        btnLogin?.setOnClickListener {
            val username = txtUsername.text.toString().trim()
            val password = txtPassword.text.toString().trim()
            val body = ValidateBody(username, password, viewModel.tokenResponse!!.request_token)
            viewModel.initValidate(URLService.tmdbApiKey, body)
            callValidateToken()
        }
    }

    private fun obtainViewModel(): LoginViewModel = obtainMovieViewModel(LoginViewModel::class.java)

    @SuppressLint("LogNotTimber")
    private fun callValidateToken() {
        viewModel.requestValidateToken.observe(this, Observer {
            when (it.status) {
                SUCCESS -> {
                    hideLoading()
                    it.data.let { data ->
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                    }
                }
                LOADING -> {
                    showGradientLoading()
                    Log.d("is LOADING", " ${it.message}")
                }
                ERROR -> {
                    it.message.let { error ->
                        Log.d("is ERROR ", "${it.message}")
                        val messageError =
                            ErrorConverter.handlerErrorConverter(error!!)
                        checkHandlerConnectionMessage(messageError)
                    }
                }
            }

        })
    }

    @SuppressLint("LogNotTimber")
    private fun initialObServe() {
        viewModel.requestNewToken.observe(this, Observer {
            when (it.status) {
                SUCCESS -> {
                    hideLoading()
                    it.data.let { data ->
                        viewModel.tokenResponse = data
                    }

                }
                LOADING -> {
                    showGradientLoading()
                    Log.d("is LOADING ", "${it.message}")
                }
                ERROR -> {
                    it.message.let { error ->
                        Log.d("is ERROR ", "${it.message}")
                        val messageError =
                            ErrorConverter.handlerErrorConverter(error!!)
                        checkHandlerConnectionMessage(messageError)
                    }
                }
            }

        })

    }


}