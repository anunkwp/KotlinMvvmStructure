package com.nankung.kotlinmvvmstructure.view.module.login

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import com.nankung.common.module.dialog.showChoiceDialog
import com.nankung.common.module.base.URLService
import com.nankung.common.module.base.mvvm.activity.AppMvvmActivity
import com.nankung.common.module.dialog.hideLoading
import com.nankung.common.module.dialog.showGradientLoading
import com.nankung.kotlinmvvmstructure.R
import com.nankung.kotlinmvvmstructure.view.module.main.MainActivity
import com.nankung.kotlinmvvmstructure.view.util.obtainViewModel
import com.nankung.network.model.body.ValidateBody
import com.nankung.network.model.exeption.ErrorConverter
import com.nankung.network.remote.Status
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppMvvmActivity() {
    private lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        viewModel = obtainViewModel()
        viewModel.initKeys(URLService.tmdbApiKey)
        initialObServe()

    }

    private fun obtainViewModel(): LoginViewModel = obtainViewModel(LoginViewModel::class.java)

    @SuppressLint("LogNotTimber")
    private fun callValidateToken() {
        viewModel.requestValidateToken.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    hideLoading()
                    it.data.let { data ->
                        Toast.makeText(this@LoginActivity, data!!.request_token, Toast.LENGTH_SHORT)
                            .show()
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                    }
                    Log.d("is SUCCESS"," ${it.message}")
                }
                Status.LOADING -> {
                    showGradientLoading()
                    Log.d("is LOADING"," ${it.message}")
                }
                Status.EMPTY -> {
                    Log.d("is EMPTY"," ${it.message}")
                }
                Status.ERROR -> {
                    it.message.let { error ->
                        val messageError =
                            ErrorConverter.handlerErrorConverter(error!!, this@LoginActivity)
                        showChoiceDialog(title = messageError!![0], message = messageError[1])
                    }

                }
            }

        })
    }

    @SuppressLint("LogNotTimber")
    private fun initialObServe() {
        viewModel.requestNewToken.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    hideLoading()
                    it.data.let { data ->
                        btnLogin.setOnClickListener {
                            val username = txtUsername.text.toString().trim()
                            val password = txtPassword.text.toString().trim()
                            val body = ValidateBody(username, password, data!!.request_token)
                            viewModel.initValidate(URLService.tmdbApiKey, body)
                            callValidateToken()

                        }
                    }
                    Log.d("is SUCCESS"," ${it.message}")
                }
                Status.LOADING -> {
                    showGradientLoading()
                    Log.d("is LOADING ","${it.message}")
                }
                Status.EMPTY -> {
                    Log.d("is EMPTY ","${it.message}")
                }
                Status.ERROR -> {
                    it.message.let { error ->
                        val messageError =
                            ErrorConverter.handlerErrorConverter(error!!, this@LoginActivity)
                        showChoiceDialog(title = messageError!![0], message = messageError[1])
                    }
                }
            }

        })

    }


}